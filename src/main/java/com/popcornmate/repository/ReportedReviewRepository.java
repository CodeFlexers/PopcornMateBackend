package com.popcornmate.repository;

import com.popcornmate.entity.ReportedReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportedReviewRepository extends JpaRepository<ReportedReview, Integer> {

    Boolean existsByUserUserCodeAndReviewReviewCode(Integer userCode, Integer reviewCode);
}
