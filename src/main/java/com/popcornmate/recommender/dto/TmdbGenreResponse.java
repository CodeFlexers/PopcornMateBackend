package com.popcornmate.recommender.dto;

import lombok.Data;

import java.util.List;

@Data
public class TmdbGenreResponse {

    private List<TmdbGenreDto> genres;

}
