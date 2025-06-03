package com.popcornmate.recommender.dto;

public class MovieHomeDto {
    private Long movieCode;
    private String poster;

    public MovieHomeDto(Long movieCode, String poster) {
        this.movieCode = movieCode;
        this.poster = poster;
    }

    public Long getMovieCode() {
        return movieCode;
    }

    public void setMovieCode(Long movieCode) {
        this.movieCode = movieCode;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = "https://image.tmdb.org/t/p/w500"+poster;
    }
}
