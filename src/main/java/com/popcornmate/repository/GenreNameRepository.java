package com.popcornmate.repository;

import com.popcornmate.entity.GenreName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreNameRepository extends JpaRepository<GenreName, Integer> {
}
