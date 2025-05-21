package com.popcornmate.review.controller;

import com.popcornmate.repository.ReviewCommentQueryRepository;
import com.popcornmate.repository.ReviewQueryRepository;
import com.popcornmate.review.dto.ReviewCommentDto;
import com.popcornmate.review.dto.ReviewCreateDto;
import com.popcornmate.review.dto.ReviewDto;
import com.popcornmate.review.service.ReviewService;
import com.popcornmate.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reviews", produces = "application/json; charset=utf8")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewQueryRepository reviewQueryRepository;
    private final ReviewCommentQueryRepository reviewCommentQueryRepository;
    public ReviewController(ReviewService reviewService, ReviewQueryRepository reviewQueryRepository, ReviewCommentQueryRepository reviewCommentQueryRepository) {
        this.reviewService = reviewService;
        this.reviewQueryRepository = reviewQueryRepository;
        this.reviewCommentQueryRepository = reviewCommentQueryRepository;
    }
    @GetMapping
    public ResponseEntity<Page<ReviewDto>> getReviewByMovieCode(@RequestParam Long movieCode, @RequestParam String sort, @RequestParam int page){
        return ResponseEntity.ok().body(reviewQueryRepository.findReviewSorted(movieCode, sort, PageRequest.of(page, 10)));
    }
    @PostMapping
    public ResponseEntity<String> createReview(@AuthenticationPrincipal CustomUserDetails user, ReviewCreateDto updateData){
        try {
            reviewService.createReview(user.getUserCode(), updateData);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PatchMapping("{reviewCode}")
    public ResponseEntity<String> updateReview(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Integer reviewCode, ReviewCreateDto updateData){
        try {
            reviewService.updateReview(user.getUserCode(), reviewCode, updateData);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("{reviewCode}")
    public ResponseEntity<String> deleteReview(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Integer reviewCode){
        try {
            reviewService.deleteReview(user.getUserCode(), reviewCode);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/reactions/{reviewCode}")
    public ResponseEntity<String> reactionReview(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Integer reviewCode, @RequestParam String reaction){
        try {
            reviewService.reactionReview(user.getUserCode(),reviewCode, reaction);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body("존재하지 않는 리뷰입니다.");
        }
    }
    @PostMapping("reports/{reviewCode}")
    public ResponseEntity<String> reportReview(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Integer reviewCode, @RequestParam String reason){
        try {
            reviewService.reportReview(user.getUserCode(), reviewCode, reason);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{reviewCode}/comments")
    public ResponseEntity<Page<ReviewCommentDto>> getReviewCommentsByPageAndReview(@PathVariable Integer reviewCode, @RequestParam int page){
        return ResponseEntity.ok().body(reviewCommentQueryRepository.findReviewCommentSorted(reviewCode, PageRequest.of(page, 10)));
    }
    @PostMapping("/{reviewCode}/comments")
    public ResponseEntity<String> createReviewComment(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Integer reviewCode, String content){
        try {
            reviewService.createReviewComment(user.getUserCode(), reviewCode, content);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PatchMapping("/{reviewCode}/comments")
    public ResponseEntity<String> updateReviewComment(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Integer reviewCode, String content, Integer reviewCommentCode){
        try {
            reviewService.updateReviewComment(user.getUserCode(), reviewCode, content, reviewCommentCode);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/comments/{reviewCode}")
    public ResponseEntity<String> deleteReviewComment(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Integer reviewCode, Integer reviewCommentCode){
        try {
            reviewService.deleteReviewComment(user.getUserCode(),reviewCode, reviewCommentCode);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
