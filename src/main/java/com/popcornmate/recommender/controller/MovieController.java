package com.popcornmate.recommender.controller;


import com.popcornmate.recommender.dto.MovieDto;
import com.popcornmate.recommender.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor // final 필드인 WebClient를 자동 생성자 주입

class MovieController {


    private final MovieService movieService;

    // 스케줄러
    @GetMapping("/recent")
    public ResponseEntity<List<MovieDto>> getRecentMovies(@RequestParam String query){

        return ResponseEntity.ok().body(movieService.getRecentMovies(query));
    }



}
