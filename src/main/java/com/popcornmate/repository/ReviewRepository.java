package com.popcornmate.repository;

import com.popcornmate.entity.Review;
import com.popcornmate.review.dto.ReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("""
            SELECT r
            FROM Review r
            
            """)
    Page<Review> getReviewByMovieCode(Pageable pageable, Long movieCode, String sort);

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.user.userCode=:userCode AND r.reviewCode=:reviewCode
            """)
    Review selectReviewCodeAndUserCode(Integer userCode, Integer reviewCode);
}
