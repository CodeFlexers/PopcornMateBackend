package com.popcornmate.recommender.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TmdbMovieDTO {

    private Long id;
    private String title;
    private String release_date;
    private List<Integer> genre_ids; // [1, 34, 314]
    private Float popularity;
    private String overview;
    private String backdrop_path;
    private boolean adult;
    private boolean video;
    private String original_language;
    private Integer vote_count;
    private Float vote_average;
    private String poster_path;
}
