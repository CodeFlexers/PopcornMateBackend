package com.popcornmate.recommender.controller;


import com.popcornmate.recommender.dto.MovieDetailDto;
import com.popcornmate.recommender.dto.MovieDto;
import com.popcornmate.recommender.dto.MovieHomeDto;
import com.popcornmate.recommender.service.MovieService;
import lombok.Getter;
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
    @PostMapping
    public ResponseEntity<?> insertMovie(@RequestParam int page){
        try {
            movieService.test(page);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/pop")
    public ResponseEntity<List<MovieHomeDto>> getMovieByPopularity(){
        return ResponseEntity.ok().body(movieService.getMovieByPopularity());
    }
    @GetMapping
    public ResponseEntity<MovieDetailDto> getMovie(@RequestParam Long movieCode){
        return ResponseEntity.ok().body(movieService.getMovie(movieCode));
    }
    @GetMapping("/new")
    public ResponseEntity<List<MovieHomeDto>> getRecentMovie(){
        return ResponseEntity.ok().body(movieService.getRecentMovie());
    }




}
