package com.popcornmate.repository;

import com.popcornmate.entity.QReviewComment;
import com.popcornmate.review.dto.ReviewCommentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewCommentQueryRepository {
    private final JPAQueryFactory queryFactory;
    @Value("${file.download-url}")
    private String url;

    public Page<ReviewCommentDto> findReviewCommentSorted(Integer reviewCode, Pageable pageable){
        QReviewComment comment = QReviewComment.reviewComment;
        List<ReviewCommentDto> content = queryFactory
                .select(Projections.constructor(
                        ReviewCommentDto.class,
                        comment.reviewCommentCode,
                        comment.content,
                        comment.wroteAt,
                        comment.isEdit,
                        comment.review.reviewCode,
                        comment.user.nickname,
                        comment.user.profileImage
                )).from(comment)
                .where(comment.review.reviewCode.eq(reviewCode))
                .orderBy(comment.wroteAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory
                .select(comment.count())
                .from(comment)
                .where(comment.review.reviewCode.eq(reviewCode))
                .fetchOne();
        return new PageImpl<>(content, pageable, total);
    }
}
