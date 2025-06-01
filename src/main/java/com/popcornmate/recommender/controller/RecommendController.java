package com.popcornmate.recommender.controller;


import com.popcornmate.recommender.dto.ActivityRecommendationDto;
import com.popcornmate.recommender.dto.RandomRecommendationDto;
import com.popcornmate.recommender.service.RecommendService;
import com.popcornmate.security.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/recommendation", produces = "application/json; charset=utf8")

@RequiredArgsConstructor
public class RecommendController {

    // 생성자 주입
    private final RecommendService recommendService;


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

         List<ActivityRecommendationDto> activityRecommendationDtos = recommendService.getUserActivityMovies(user.getUserCode());

         // movieCode로 영화의 카테고리만 조회, 카테고리별 개수 *

         return null;
    }


    // 취향 비슷한 사람들이 시청한 영화 추천


    // 내가 본 영화 히스토리 시각화 - 연도별, 장르변


}
