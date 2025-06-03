package com.popcornmate.recommender.service;

import com.popcornmate.entity.Genre;
import com.popcornmate.entity.Movie;
import com.popcornmate.recommender.dto.*;
import com.popcornmate.recommender.dto.test.TmdbMovieDto;
import com.popcornmate.recommender.dto.test.TmdbResponse;
import com.popcornmate.repository.MovieRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final WebClient tmdbWebClient;
    private final MovieRepository movieRepository;
    private final EntityManager entityManager;
    @Value("${tmdb.api.key}")
    private String apiKey;

    public MovieService(WebClient tmdbWebClient, MovieRepository movieRepository, EntityManager entityManager) {
        this.tmdbWebClient = tmdbWebClient;
        this.movieRepository = movieRepository;
        this.entityManager = entityManager;
    }


    @Transactional
    public void getRecentMovies() {
        try {
            // 영화 목록 가져오기
            TmdbMovieResponse response = tmdbWebClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/discover/movie")
                            .build())
                    .retrieve()
                    .bodyToMono(TmdbMovieResponse.class)
                    .block();

            // 장르 매핑용 목록 가져오기
            TmdbGenreResponse genreRes = tmdbWebClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/genre/movie/list")
                            .build())
                    .retrieve()
                    .bodyToMono(TmdbGenreResponse.class)
                    .block();

            // 장르 ID → 이름 매핑
            Map<Integer, String> genreMap = genreRes.getGenres().stream()
                    .collect(Collectors.toMap(g -> g.getId(), g -> g.getName()));

            if (response == null || response.getResults() == null) return;

            List<Movie> movies = new ArrayList<>();

            for (TmdbMovieDTO res : response.getResults()) {

                Movie movie = new Movie(
                        res.getId().longValue(),
                        res.getTitle(),
                        res.getOverview(),
                        res.getRelease_date(), // "yyyy-MM-dd"
                        res.getPopularity(),
                        res.getBackdrop_path(),
                        res.getOriginal_language(),
                        res.getPoster_path(),
                        res.isVideo(),
                        res.isAdult(),
                        res.getVote_average(),
                        res.getVote_count(),
                        new ArrayList<>()
                );

                for (Integer genreCode : res.getGenre_ids()) {
                    String name = genreMap.getOrDefault(genreCode, "Unknown");

                    boolean exists = movie.getGenres().stream()
                            .anyMatch(g -> g.getGenreCode().equals(genreCode));

                    if (!exists) {
                        Genre genre = new Genre(genreCode, name, movie);
                        movie.getGenres().add(genre);
                    }
                }

                movies.add(movie);
            }

            movieRepository.saveAll(movies);
            movieRepository.flush();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void test(int page) {
        Map<Integer, String> genreCodeMap = Map.ofEntries(
                Map.entry(28, "액션"),
                Map.entry(12, "모험"),
                Map.entry(16, "애니메이션"),
                Map.entry(35, "코미디"),
                Map.entry(80, "범죄"),
                Map.entry(99, "다큐멘터리"),
                Map.entry(18, "드라마"),
                Map.entry(10751, "가족"),
                Map.entry(14, "판타지"),
                Map.entry(36, "역사"),
                Map.entry(27, "호러"),
                Map.entry(10402, "음악"),
                Map.entry(9648, "미스터리"),
                Map.entry(10749, "로맨스"),
                Map.entry(878, "공상 과학"),
                Map.entry(10770, "TV 영화"),
                Map.entry(53, "스릴러"),
                Map.entry(10752, "전쟁"),
                Map.entry(37, "서부")
        );

        String url = "https://api.themoviedb.org/3/movie/popular?language=ko&page=" + page;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey); // <- Bearer prefix 추가
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate rest = new RestTemplate();
        HttpEntity<String> head = new HttpEntity<>(headers);

        ResponseEntity<TmdbResponse> response = rest.exchange(
                url,
                HttpMethod.GET,
                head,
                TmdbResponse.class
        );

        TmdbResponse tmdbResponse = response.getBody();
        if (tmdbResponse != null && tmdbResponse.getResults() != null) {
            System.out.println("반복을 시작합니다.");
            for (TmdbMovieDto dto : tmdbResponse.getResults()) {
                try {
                    saveSingleMovie(dto, genreCodeMap);
                } catch (Exception e) {
                    System.err.println("저장 실패: " + dto.getTitle() + " → " + e.getMessage());
                }
            }
        }
    }
    @Transactional
    public void saveSingleMovie(TmdbMovieDto dto, Map<Integer, String> genreCodeMap) {
        if (movieRepository.existsById(dto.getId())) return;

        List<Genre> genres = new ArrayList<>();

        for (Integer genreId : dto.getGenre_ids()) {
            String name = genreCodeMap.get(genreId);

            // 매핑 안 되면 skip
            if (name == null || name.isBlank()) continue;

            Genre genre = new Genre();
            genre.setGenreCode(genreId);
            genre.setName(name);
            // movie는 아래에서 설정
            genres.add(genre);
        }

        Movie movie = new Movie(
                dto.getId(),
                dto.getTitle(),
                dto.getOverview(),
                dto.getRelease_date(),
                dto.getPopularity(),
                dto.getBackdrop_path(),
                dto.getOriginal_language(),
                dto.getPoster_path(),
                dto.isVideo(),
                dto.isAdult(),
                dto.getVote_average(),
                dto.getVote_count(),
                genres
        );

        // 장르에 movie 객체 연결
        for (Genre genre : genres) {
            genre.setMovie(movie);
        }

        movieRepository.save(movie);
        entityManager.flush();
    }
    public Movie toEntity(TmdbMovieDto dto, Map<Integer, String> genreCodeMap) {
        Movie movie = new Movie(
                dto.getId(),
                dto.getTitle(),
                dto.getOverview(),
                dto.getRelease_date(),
                dto.getPopularity(),
                dto.getBackdrop_path(),
                dto.getOriginal_language(),
                dto.getPoster_path(),
                dto.isVideo(),
                dto.isAdult(),
                dto.getVote_average(),
                dto.getVote_count(),
                new ArrayList<>()
        );

        for (Integer genreId : dto.getGenre_ids()) {
            if(!genreCodeMap.containsKey(genreId)){
                System.out.println("Unknown genre id : "+genreId + " for movie: "+dto.getTitle());
                continue;
            }
            String name = genreCodeMap.getOrDefault(genreId, "Unknown");
            movie.getGenres().add(new Genre(genreId, name, movie));
        }

        return movie;
    }
    public List<MovieHomeDto> getMovieByPopularity(){
        List<Movie> movie = movieRepository.getMovieByPopularity();
        List<MovieHomeDto> dtos = new ArrayList<>();
        for(Movie m : movie){
            MovieHomeDto md = new MovieHomeDto(
                    m.getMovieCode(),
                    m.getPosterPath()
            );
            dtos.add(md);
            System.out.println(m.getPosterPath());
        }
        return dtos;
    }

    public MovieDetailDto getMovie(Long movieCode) {
        Movie movie = movieRepository.findById(movieCode).orElseThrow();
        List<String> genreName = new ArrayList<>();
        genreName = movie.getGenres().stream().map(Genre::getName).toList();
        MovieDetailDto dto = new MovieDetailDto(
            movie.getMovieCode(),
            movie.getTitle(),
            movie.getReleaseDate(),
                genreName,
                movie.getPopularity(),
                movie.getOverview(),
                movie.getBackdropPath(),
                movie.isAdult(),
                movie.isVideo(),
                movie.getOriginalLanguage(),
                movie.getVoteCount(),
                Double.valueOf(movie.getVoteAverage()),
                "https://image.tmdb.org/t/p/w780"+movie.getPosterPath()
        );
        return dto;
    }
    public List<MovieHomeDto> getRecentMovie(){
        List<Movie> movie = movieRepository.getRecentMovie();
        List<MovieHomeDto> dto = new ArrayList<>();
        for(Movie m : movie){
            MovieHomeDto dt = new MovieHomeDto(
                    m.getMovieCode(),
                    m.getPosterPath()
            );
            dto.add(dt);
        }
        return dto;
    }
}
