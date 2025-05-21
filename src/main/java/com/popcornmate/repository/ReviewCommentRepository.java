package com.popcornmate.repository;

import com.popcornmate.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {
    ReviewComment findByReviewCommentCodeAndReviewReviewCodeAndUserUserCode(Integer reviewCommentCode, Integer reviewCode, Integer userCode);
}
