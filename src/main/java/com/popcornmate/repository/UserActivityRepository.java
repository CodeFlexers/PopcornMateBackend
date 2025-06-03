package com.popcornmate.repository;

import com.popcornmate.entity.User;
import com.popcornmate.entity.UserActivity;
import com.popcornmate.recommender.dto.UserActivityGenresDto;
import com.popcornmate.recommender.dto.UserGenresDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {

    @Query(value = """
             SELECT combined.genre_code AS genreCode,
                    combined.name AS name,
                    SUM(combined.genre_count) AS genreCount,
                    SUM(combined.time_like) AS timeLike,
                    SUM(combined.genre_count) * SUM(combined.time_like) AS wait
                FROM (
                    SELECT g.genre_code, g.name,
                    count(g.genre_code) AS genre_count,
                    sum(ua.time_on_page) AS time_like
                    FROM user_activity ua
                    JOIN movie m ON ua.movie_code = m.movie_code
                    JOIN genre g ON m.movie_code = g.movie_code
                    WHERE ua.user_code = :userCode
                    GROUP BY g.genre_code
                    UNION ALL
                    SELECT g.genre_code, g.name,
                     count(g.genre_code) AS genre_count,
                     SUM(like_score) AS time_like
                    FROM movie_history mh
                    JOIN movie m ON mh.movie_code = m.movie_code
                    JOIN genre g ON m.movie_code = g.movie_code
                    WHERE mh.user_code = :userCode
                    GROUP BY g.genre_code
                ) AS combined
                GROUP BY combined.genre_code;
        """, nativeQuery = true)
    List<UserGenresProjection> findAllGenresCountByUserCode(Integer userCode);


//    @Query("""
//            SELECT
//            new com.popcornmate.recommender.dto.UserActivityGenresDto(
//                g.genreCode, g.name,
//                COUNT(DISTINCT ua.movie.movieCode),
//                SUM(ua.timeOnPage))
//            FROM UserActivity ua
//            JOIN ua.movie m
//            JOIN m.genres g
//            WHERE ua.userCode = :userCode
//            GROUP BY g.genreCode
//        """)
//    List<UserActivityGenresDto> findAllGenresByUserCode(Integer userCode);
}
