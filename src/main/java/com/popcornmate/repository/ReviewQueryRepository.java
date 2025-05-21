package com.popcornmate.repository;

import com.popcornmate.entity.QReview;
import com.popcornmate.entity.QReviewComment;
import com.popcornmate.entity.QReviewReaction;
import com.popcornmate.entity.ReviewReactionEnum;
import com.popcornmate.review.dto.ReviewDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
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
        QReviewComment comment = QReviewComment.reviewComment;
        NumberExpression<Long> likeCountExpr = new CaseBuilder()
                .when(reaction.reaction.eq(ReviewReactionEnum.like)).then(1L)
                .otherwise(0L)
                .sum();

        NumberExpression<Long> dislikeCountExpr = new CaseBuilder()
                .when(reaction.reaction.eq(ReviewReactionEnum.dislike)).then(1L)
                .otherwise(0L)
                .sum();
        List<Tuple> tuples = queryFactory
                .select(
                        review.reviewCode,
                        review.content,
                        review.rate,
                        review.wroteAt,
                        review.isEdit,
                        review.movie.movieCode,
                        review.user.nickname,
                        review.user.profileImage,
                        comment.countDistinct(),
                        likeCountExpr,
                        dislikeCountExpr
                )
                .from(review)
                .leftJoin(reaction).on(reaction.review.eq(review))
                .leftJoin(comment).on(comment.review.eq(review))
                .where(review.movie.movieCode.eq(movieCode))
                .groupBy(review.reviewCode)
                .orderBy(getSortOrder(sort, review, reaction))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        List<ReviewDto> content = tuples.stream()
                .map(t -> new ReviewDto(
                        t.get(review.reviewCode),
                        t.get(review.content),
                        t.get(review.rate),
                        t.get(review.wroteAt),
                        t.get(review.isEdit),
                        t.get(review.movie.movieCode),
                        t.get(review.user.nickname),
                        t.get(review.user.profileImage),
                        t.get(comment.countDistinct()),
                        t.get(likeCountExpr),    // 좋아요 수
                        t.get(dislikeCountExpr)    // 싫어요 수
                )).toList();
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
