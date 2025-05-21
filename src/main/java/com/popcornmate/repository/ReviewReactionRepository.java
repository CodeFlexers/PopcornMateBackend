package com.popcornmate.repository;

import com.popcornmate.entity.ReviewReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewReactionRepository extends JpaRepository<ReviewReaction, Integer> {

    @Query("""
            SELECT r
            FROM ReviewReaction r
            WHERE r.user.userCode=:userCode AND r.review.reviewCode=:reviewCode
            """)
    ReviewReaction selectUserCodeAndReviewCode(Integer userCode, Integer reviewCode);
}
