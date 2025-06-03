package com.popcornmate.recommender.controller;


import com.popcornmate.recommender.dto.ActivityRecommendationDto;
import com.popcornmate.recommender.dto.MovieHomeDto;
import com.popcornmate.recommender.dto.RandomRecommendationDto;
import com.popcornmate.recommender.service.RecommendService;
import com.popcornmate.security.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/recommendation", produces = "application/json; charset=utf8")

@RequiredArgsConstructor
public class RecommendController {

    // 생성자 주입
    private final RecommendService recommendService;


    // 영화가 마음에 드는지, 별로인지
    @PostMapping("/{movieCode}/like")
    public ResponseEntity<String> createMovieLike(@AuthenticationPrincipal CustomUserDetails user,
                                                  @PathVariable Long movieCode,
                                                  @RequestParam int likeScore){

        // 장르, 분위기 선택에 따른 랜덤 영화 추천
        // 분위기는 감동적인, 슬픈, 유쾌한, 무서운, 따뜻한, - 장르 랜덤
        String result = recommendService.createMovieLike(user.getUserCode(),movieCode, likeScore);

        return ResponseEntity.ok().body(result);
    }


    // 장르나 분위기 설정하면 랜덤 영화 추천
    // 프론트에서 genre_code를 받을 예정
    @GetMapping("/random")
    public ResponseEntity<?> getRandomMovie(@RequestParam Integer genreCode){

        // 장르, 분위기 선택에 따른 랜덤 영화 추천
        // 분위기는 감동적인, 슬픈, 유쾌한, 무서운, 따뜻한, - 장르 랜덤
        RandomRecommendationDto randomRecommendationDtos = recommendService.getRandomMovie(genreCode);

        return ResponseEntity.ok().body("랜덤 영화: " + randomRecommendationDtos);
    }


    // userActivity 사용자 활동 기반으로 영화 추천
    @GetMapping("/activities")
    public ResponseEntity<?> getUserActivityMovies(@AuthenticationPrincipal CustomUserDetails user){

         List<MovieHomeDto> activityRecommendationDtos = recommendService.getUserActivityMovies(user.getUserCode());

         // movieCode로 영화의 카테고리만 조회, 카테고리별 개수 *

         return ResponseEntity.ok().body(activityRecommendationDtos);
    }


    // 취향 비슷한 사람들이 시청한 영화 추천


    // 내가 본 영화 히스토리 시각화 - 연도별, 장르변


}
