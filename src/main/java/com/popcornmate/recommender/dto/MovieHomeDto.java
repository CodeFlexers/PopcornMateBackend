package com.popcornmate.recommender.dto;

import lombok.Getter;

@Getter
public class MovieHomeDto {
    private Long movieCode;
    private String poster;
    private boolean isNew;
    private boolean isAdult;

    public MovieHomeDto(Long movieCode, String poster) {
        this.movieCode = movieCode;
        this.poster = poster;
    }

    public MovieHomeDto(Long movieCode, String poster, boolean isNew, boolean isAdult) {
        this.movieCode = movieCode;
        this.poster = poster;
        this.isNew = isNew;
        this.isAdult = isAdult;
    }
}
