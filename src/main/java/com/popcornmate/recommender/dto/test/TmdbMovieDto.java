package com.popcornmate.recommender.dto.test;

import lombok.Data;

import java.util.List;

@Data
public class TmdbMovieDto {
    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids;
    private long id;
    private String original_language;
    private String original_title;
    private String overview;
    private float popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private float vote_average;
    private int vote_count;
}