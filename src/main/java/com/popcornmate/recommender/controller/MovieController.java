package com.popcornmate.recommender.controller;


import com.popcornmate.recommender.dto.MovieDto;
import com.popcornmate.recommender.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/movies", produces = "application/json; charset=utf8")
@RequiredArgsConstructor // final 필드인 WebClient를 자동 생성자 주입

class MovieController {


    private final MovieService movieService;

    // 스케줄러
    @GetMapping("/recent")
    public ResponseEntity<String> getRecentMovies(){
        movieService.getRecentMovies();
        return ResponseEntity.ok().body("db확인해보셈");
    }



}
