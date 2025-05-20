package com.popcornmate.review.service;

import com.popcornmate.entity.*;
import com.popcornmate.repository.*;
import com.popcornmate.review.dto.ReviewCreateDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReviewReactionRepository reviewReactionRepository;
    private final ReportedReviewRepository reportedReviewRepository;
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository, ReviewReactionRepository reviewReactionRepository, ReportedReviewRepository reportedReviewRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reviewReactionRepository = reviewReactionRepository;
        this.reportedReviewRepository = reportedReviewRepository;
    }
    @Transactional
    public void createReview(Integer userCode, ReviewCreateDto updateData) throws Exception {
        try {
            User user = userRepository.getReferenceById(userCode);
            Movie movie = movieRepository.getReferenceById(updateData.getMovieCode());
            Review review = new Review();
            review.setRate(updateData.getRate());
            review.setContent(updateData.getContent());
            review.setMovie(movie);
            review.setWroteAt(LocalDateTime.now());
            review.setEdit(false);
            review.setUser(user);
            reviewRepository.save(review);
        } catch (Exception e){
            throw new Exception("에러 처리 하자");
        }
    }
    @Transactional
    public void updateReview(Integer userCode, Integer reviewCode, ReviewCreateDto updateData) throws Exception{
        try{
            Review review = reviewRepository.selectReviewCodeAndUserCode(userCode, reviewCode);
            float rate = updateData.getRate()==0f ? review.getRate(): updateData.getRate();
            String content = updateData.getContent()==null ? review.getContent() : updateData.getContent();
            review.setRate(rate);
            review.setContent(content);
            review.setEdit(true);
            reviewRepository.save(review);
        } catch (Exception e){
            throw new Exception(e.getMessage() + " : "+"수정 요청 유저와 리뷰 작성 유저가 다를 가능성이 큽니다.");
        }
    }
    @Transactional
    public void deleteReview(Integer userCode, Integer reviewCode) throws Exception {
        try {
            Review review = reviewRepository.findById(reviewCode).orElseThrow();
            if(review.getUser().getUserCode().equals(userCode)){
                reviewRepository.delete(review);
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception("에러 처리");
        }
    }
    @Transactional
    public void reactionReview(Integer userCode, Integer reviewCode, String reaction) {
        ReviewReaction reviewReaction = reviewReactionRepository.selectUserCodeAndReviewCode(userCode,reviewCode);
        Review review = reviewRepository.getReferenceById(reviewCode);
        if(reviewReaction==null){ //한 번도 누른적 없음
            ReviewReaction r = new ReviewReaction();
            r.setReview(review);
            r.setReactedTime(LocalDateTime.now());
            r.setUser(userRepository.getReferenceById(userCode));
            r.setReaction(ReviewReactionEnum.valueOf(reaction));
            reviewReactionRepository.save(r);
        } else if( reviewReaction.getReaction().name().equals("like")|| reviewReaction.getReaction().name().equals("dislike")) {    //이미 좋아요, 싫어요 누름
            reviewReactionRepository.delete(reviewReaction);
        }
    }
    @Transactional
    public void reportReview(Integer userCode, Integer reviewCode, String reason) {
        //이미 같은 유저가 신고 했는지 확인
        Boolean isAlreadyReported = reportedReviewRepository.existsByUserUserCodeAndReviewReviewCode(userCode, reviewCode);
        if(!isAlreadyReported){ //없으면
            ReportedReview r = new ReportedReview();
            r.setReason(reason);
            r.setReview(reviewRepository.getReferenceById(reviewCode));
            r.setUser(userRepository.getReferenceById(userCode));
            reportedReviewRepository.save(r);
        } else {    // 있으면
            System.out.println("이미 신고함");
        }
        //이 리뷰에 신고가 n건 있습니다 return
    }
}
