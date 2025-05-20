package com.popcornmate.track.repository;

import com.popcornmate.entity.MovieHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieHistoryRepository extends JpaRepository<MovieHistory, Integer> {
}
