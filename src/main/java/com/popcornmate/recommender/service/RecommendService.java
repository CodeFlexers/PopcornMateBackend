package com.popcornmate.recommender.service;


import com.popcornmate.entity.Genre;
import com.popcornmate.entity.Movie;
import com.popcornmate.entity.MovieHistory;
import com.popcornmate.entity.UserActivity;
import com.popcornmate.recommender.dto.ActivityRecommendationDto;
import com.popcornmate.recommender.dto.MovieHistoryGenresDto;
import com.popcornmate.recommender.dto.RandomRecommendationDto;
import com.popcornmate.recommender.dto.UserActivityGenresDto;
import com.popcornmate.repository.MovieHistoryRepository;
import com.popcornmate.repository.MovieRepository;
import com.popcornmate.repository.UserActivityRepository;
import com.popcornmate.track.service.UserActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendService {

    private final MovieHistoryRepository movieHistoryRepository;
    private final UserActivityRepository userActivityRepository;
    private final MovieRepository movieRepository;

    public RecommendService(MovieHistoryRepository movieHistoryRepository, MovieRepository movieRepository, UserActivityRepository userActivityRepository) {
        this.movieHistoryRepository = movieHistoryRepository;
        this.movieRepository = movieRepository;
        this.userActivityRepository = userActivityRepository;
    }


    @Transactional
    public RandomRecommendationDto getRandomMovie(Integer genreCode) {

        // 받은 장르 코드의 영화 조회
        List<Movie> movies = movieRepository.findAllByGenreCode(genreCode);

        // 랜덤 하나
        Movie randomMovie = movies.get(0);

        return new RandomRecommendationDto(
                randomMovie.getMovieCode(),
                randomMovie.getTitle(),
                randomMovie.getReleaseDate(),
                randomMovie.getGenres().stream().map(Genre::getName).toList(),
                randomMovie.getPopularity(),
                randomMovie.getOverview(),
                randomMovie.getBackdropPath(),
                randomMovie.isAdult(),
                randomMovie.isVideo(),
                randomMovie.getOriginalLanguage(),
                randomMovie.getVoteCount(),
                randomMovie.getVoteAverage(),
                randomMovie.getPosterPath()
        );

    }


    @Transactional
    public List<ActivityRecommendationDto> getUserActivityMovies(Integer userCode) {

        // 영화 카테고리 코드
        List<UserActivityGenresDto> genres = userActivityRepository.findAllGenresByUserCode(userCode);
        System.out.println("genres = " + genres);

        // 가중치 계산: genre_count * time_on_page
        for(UserActivityGenresDto genre : genres) {
            int res = (int) (genre.getGenreCount() * genre.getTimeCount());
        }


        // 시청한 영화 기록 가져오기
        List<MovieHistoryGenresDto> movieHistories = movieHistoryRepository.findAllGenresByUserCode(userCode);
        System.out.println("movieHistories = " + movieHistories);
        // 가중치 계산: likeScore




        return null;

    }
}
