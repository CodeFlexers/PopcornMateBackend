package com.popcornmate.repository;

import com.popcornmate.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.genreCode = :genreCode ORDER BY FUNCTION('RAND')")
    List<Movie> findAllByGenreCode(Integer genreCode);
}
