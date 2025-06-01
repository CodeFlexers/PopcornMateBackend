package com.popcornmate.repository;

import com.popcornmate.entity.MovieHistory;
import com.popcornmate.recommender.dto.MovieHistoryGenresDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MovieHistoryRepository extends JpaRepository<MovieHistory, Integer> {

    @Query("SELECT m FROM MovieHistory m WHERE m.userCode = :userCode")
    List<MovieHistory> findAllByUserCode(Integer userCode);

    @Query("""
            SELECT
            new com.popcornmate.recommender.dto.MovieHistoryGenresDto(
                g.genreCode
                , g.name
                , COUNT(g.genreCode))
            FROM MovieHistory mh
            JOIN mh.movie m
            JOIN m.genres g
            WHERE mh.userCode = :userCode
            GROUP BY g.genreCode
            """)
    List<MovieHistoryGenresDto> findAllGenresByUserCode(Integer userCode);


}
