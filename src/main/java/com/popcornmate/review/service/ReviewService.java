package com.popcornmate.review.service;

import com.popcornmate.entity.*;
import com.popcornmate.repository.*;
import com.popcornmate.review.dto.ReviewCommentDto;
import com.popcornmate.review.dto.ReviewCreateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReviewReactionRepository reviewReactionRepository;
    private final ReportedReviewRepository reportedReviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository, ReviewReactionRepository reviewReactionRepository, ReportedReviewRepository reportedReviewRepository, ReviewCommentRepository reviewCommentRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reviewReactionRepository = reviewReactionRepository;
        this.reportedReviewRepository = reportedReviewRepository;
        this.reviewCommentRepository = reviewCommentRepository;
    }
    @Transactional
    public void createReview(Integer userCode, ReviewCreateDto updateData) {
            Review review = new Review(
                    updateData.getContent(),
                    updateData.getRate(),
                    movieRepository.getReferenceById(updateData.getMovieCode()),
                    LocalDateTime.now(),
                    false,
                    userRepository.getReferenceById(userCode)
            );
            reviewRepository.save(review);
    }
    @Transactional
    public void updateReview(Integer userCode, Integer reviewCode, ReviewCreateDto updateData) {
        Review review = reviewRepository.selectReviewCodeAndUserCode(userCode, reviewCode);

        if(!review.getUser().getUserCode().equals(userCode)){
            throw new AccessDeniedException("본인이 작성한 리뷰만 수정할 수 있습니다.");
        }

        float rate = updateData.getRate()==0f ? review.getRate(): updateData.getRate();
        String content = updateData.getContent()==null ? review.getContent() : updateData.getContent();

        review.setRate(rate);
        review.setContent(content);
        review.setEdit(true);

        reviewRepository.save(review);
    }
    @Transactional
    public void deleteReview(Integer userCode, Integer reviewCode) {
        Review review = reviewRepository.findById(reviewCode).orElseThrow();
        if(!review.getUser().getUserCode().equals(userCode)){
            throw new AccessDeniedException("본인이 작성한 리뷰만 삭제할 수 있습니다.");
        }
        reviewRepository.delete(review);
    }
    @Transactional
    public String reactionReview(Integer userCode, Integer reviewCode, String reaction) {
        ReviewReactionEnum requested = Arrays.stream(ReviewReactionEnum.values())
                .filter(e -> e.name().equals(reaction.trim()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("지원하지 않는 반응: " + reaction));
        Review review;
        User user;
        try {
            review = reviewRepository.getReferenceById(reviewCode);
            user = userRepository.getReferenceById(userCode);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("유저, 또는 리뷰가 존재하지 않습니다.");
        }

        ReviewReaction existing = reviewReactionRepository.selectUserCodeAndReviewCode(userCode, reviewCode);

        // 없으면 새로 생성
        if (existing == null) {
            ReviewReaction newReaction = new ReviewReaction(
                    review,
                    user,
                    LocalDateTime.now(),
                    requested
            );
            reviewReactionRepository.save(newReaction);
            return requested.name();
        }
        ReviewReactionEnum current = existing.getReaction();
        // 동일한 반응이면 삭제
        if (current == requested) {
            String res = current.name();
            reviewReactionRepository.delete(existing);
            return "delete_"+res; // React NONE
        }

        // 반대 반응이면 값만 교체
        existing.setReaction(requested);
        existing.setReactedTime(LocalDateTime.now());
        return "change_"+current;
    }
    @Transactional
    public void reportReview(Integer userCode, Integer reviewCode, String reason) {
        boolean alreadyReported = reportedReviewRepository.existsByUserUserCodeAndReviewReviewCode(userCode, reviewCode);

        if (alreadyReported) {
            throw new EntityNotFoundException("이미 해당 리뷰를 신고했습니다.");
        }
        try {
            Review review = reviewRepository.getReferenceById(reviewCode);
            User user = userRepository.getReferenceById(userCode);
            ReportedReview r = new ReportedReview(reason, review, user);
            reportedReviewRepository.save(r);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("리뷰 또는 유저가 존재하지 않습니다.");
        }
    }
    @Transactional
    public ReviewCommentDto createReviewComment(Integer userCode, Integer reviewCode, String content) {
        Review review;
        User user;
        try {
            review = reviewRepository.getReferenceById(reviewCode);
            user = userRepository.getReferenceById(userCode);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("리뷰 또는 유저가 존재하지 않습니다.");
        }

        ReviewComment comment = new ReviewComment(content,false,LocalDateTime.now(),user,review);

        reviewCommentRepository.save(comment);
        ReviewCommentDto dto = new ReviewCommentDto(
                comment.getReviewCommentCode(),
                comment.getContent(),
                LocalDateTime.now(),
                comment.isEdit(),
                comment.getReview().getReviewCode(),
                comment.getUser().getNickname(),
                comment.getUser().getProfileImage()
        );
        return dto;
    }
    @Transactional
    public void updateReviewComment(Integer userCode, Integer reviewCode, String content, Integer reviewCommentCode) {
        ReviewComment reviewComment = reviewCommentRepository
                .findByReviewCommentCodeAndReviewReviewCodeAndUserUserCode(reviewCommentCode, reviewCode, userCode);
        if(reviewComment==null){
            throw new EntityNotFoundException("댓글이 존재하지 않거나 권한이 없습니다.");
        }
        reviewComment.setContent(content);
        reviewComment.setEdit(true);
        reviewCommentRepository.save(reviewComment);
    }
    @Transactional
    public void deleteReviewComment(Integer userCode, Integer reviewCode, Integer reviewCommentCode) {
        ReviewComment reviewComment = reviewCommentRepository
                .findByReviewCommentCodeAndReviewReviewCodeAndUserUserCode(reviewCommentCode, reviewCode, userCode);
        if(reviewComment==null){
            throw new EntityNotFoundException("댓글이 존재하지 않거나 권한이 없습니다.");
        }
        reviewCommentRepository.delete(reviewComment);
    }
}