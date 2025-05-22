package com.popcornmate.recommender.service;

import com.popcornmate.entity.Genre;
import com.popcornmate.entity.Movie;
import com.popcornmate.recommender.dto.TmdbGenreResponse;
import com.popcornmate.repository.MovieRepository;
import com.popcornmate.recommender.dto.TmdbMovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final 필드인 WebClient를 자동 생성자 주입
public class MovieService {

    private final WebClient tmdbWebClient;
    private final MovieRepository movieRepository;


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

            for (var res : response.getResults()) {

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







}
