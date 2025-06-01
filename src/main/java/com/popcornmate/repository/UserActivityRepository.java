package com.popcornmate.repository;

import com.popcornmate.entity.User;
import com.popcornmate.entity.UserActivity;
import com.popcornmate.recommender.dto.UserActivityGenresDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {

//    @Query("""
//            SELECT
//            new com.popcornmate.recommender.dto.UserActivityGenresDto(
//                g.genreCode, g.name,
//                COUNT(DISTINCT ua.movieCode),
//                SUM(DISTINCT ua.timeOnPage))
//            FROM UserActivity ua
//            JOIN Movie m ON ua.movieCode = m.movieCode
//            JOIN Genre g ON m.movieCode = g.movieCode
//            WHERE ua.userCode = :userCode
//            GROUP BY g.genreCode
//        """)
//    List<UserActivityGenresDto> findAllGenresByUserCode(Integer userCode);


    @Query("""
            SELECT
            new com.popcornmate.recommender.dto.UserActivityGenresDto(
                g.genreCode, g.name,
                COUNT(DISTINCT ua.movie.movieCode),
                SUM(ua.timeOnPage))
            FROM UserActivity ua
            JOIN ua.movie m
            JOIN m.genres g
            WHERE ua.userCode = :userCode
            GROUP BY g.genreCode
        """)
    List<UserActivityGenresDto> findAllGenresByUserCode(Integer userCode);
}
