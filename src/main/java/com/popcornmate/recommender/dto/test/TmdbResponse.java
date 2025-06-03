package com.popcornmate.recommender.dto.test;

import lombok.Data;

import java.util.List;

@Data
public class TmdbResponse {
    private int page;
    private List<TmdbMovieDto> results;
    private int total_pages;
    private int total_results;
}
