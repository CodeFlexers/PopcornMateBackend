package com.popcornmate.recommender.service;

import com.popcornmate.entity.GenreName;
import com.popcornmate.entity.Movie;
import com.popcornmate.recommender.dto.MovieDto;
import com.popcornmate.recommender.dto.TmdbGenreResponse;
import com.popcornmate.repository.GenreNameRepository;
import com.popcornmate.repository.MovieRepository;
import com.popcornmate.recommender.dto.TmdbMovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor // final 필드인 WebClient를 자동 생성자 주입
public class MovieService {

    private final WebClient tmdbWebClient;
    private final MovieRepository movieRepository;
    private final GenreNameRepository genreNameRepository;


    @Transactional
    public List<MovieDto> getRecentMovies(String query) {

        // get 요청
        TmdbMovieResponse response = tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/discover/movie")
//                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(TmdbMovieResponse.class)
                .block();


        // genreName 엔티티 genreNameId, name
        // get 요청
        TmdbGenreResponse genreRes = tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/genre/movie/list")
                        .build())
                .retrieve()
                .bodyToMono(TmdbGenreResponse.class)
                .block();

        List<GenreName> genreNames = new ArrayList<>();

        if(genreRes != null){
            System.out.println("genreRes = " + genreRes);

            genreNames = genreRes.getGenres().stream()
                    .map(res ->
                            new GenreName(res.getId(), res.getName())
                    ).toList();

            System.out.println("genreNames = " + genreNames);
            genreNameRepository.saveAll(genreNames);

        }else{
            genreNames = Collections.emptyList();
        }




        List<Movie> movies = new ArrayList<>();
        // dto -> entity
        if(response != null){

//            System.out.println(response);

            movies = response.getResults().stream()
                    .map(tmdbMovieDto ->
                        Movie.builder()
                                .movieCode(tmdbMovieDto.getId())
                                .title(tmdbMovieDto.getTitle())
                                .releaseDate(LocalDate.parse(tmdbMovieDto.getRelease_date())) // or formatter 사용
//                                .genres(tmdbMovieDto.getGenre_ids())
                                .popularity(tmdbMovieDto.getPopularity())
                                .overview(tmdbMovieDto.getOverview())
                                .backdropPath(tmdbMovieDto.getBackdrop_path())
//                                .adult(tmdbMovieDto.isAdult())
//                                .video(tmdbMovieDto.isVideo())
                                .originalLanguage(tmdbMovieDto.getOriginal_language())
                                .voteCount(tmdbMovieDto.getVote_count())
                                .voteAverage(tmdbMovieDto.getVote_average())
                                .posterPath(tmdbMovieDto.getPoster_path())
                                .build()
                    ).toList();
        }else{
            movies = Collections.emptyList();
        }





        if(response != null){

            System.out.println(response);
            return response.getResults().stream()
                    .map(movie -> {
                        MovieDto dto = new MovieDto();
                        dto.setMovieCode(movie.getId());
                        dto.setTitle(movie.getTitle());
                        dto.setReleaseDate(LocalDate.parse(movie.getRelease_date()));
                        dto.setGenres(movie.getGenre_ids());
                        dto.setPopularity(movie.getPopularity());

                        return dto;
                    }).toList();
        }else{
            return Collections.emptyList();
        }

    }






}
