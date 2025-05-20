package com.popcornmate.repository;

import com.popcornmate.entity.QReview;
import com.popcornmate.entity.QReviewReaction;
import com.popcornmate.review.dto.ReviewDto;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewQueryRepository {
    private final JPAQueryFactory queryFactory;
    public Page<ReviewDto> findReviewSorted(Long movieCode, String sort, Pageable pageable){
        QReview review = QReview.review;
        QReviewReaction reaction = QReviewReaction.reviewReaction;
        List<ReviewDto> content = queryFactory
                .select(Projections.constructor(
                        ReviewDto.class,
                        review.reviewCode,
                        review.content,
                        review.rate,
                        review.wroteAt,
                        review.isEdit,
                        review.movie.movieCode,
                        review.user.nickname,
                        review.user.profileImage
                )).from(review)
                .leftJoin(reaction).on(reaction.review.eq(review))
                .where(review.movie.movieCode.eq(movieCode))
                .groupBy(review.reviewCode)
                .orderBy(getSortOrder(sort, review, reaction))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(review.count())
                .from(review)
                .where(review.movie.movieCode.eq(movieCode))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
    private OrderSpecifier<?> getSortOrder(String sort, QReview review, QReviewReaction reaction) {
        return switch (sort) {
            case "pop" -> reaction.count().desc();
            case "new" -> review.wroteAt.asc();
            case "new_desc" -> review.wroteAt.desc();
            case "rate" -> review.rate.asc();
            case "rate_desc" -> review.rate.desc();
            default -> review.wroteAt.desc();
        };
    }
}
