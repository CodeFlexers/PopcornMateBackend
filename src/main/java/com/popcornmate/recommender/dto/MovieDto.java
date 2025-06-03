package com.popcornmate.recommender.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieDto {

    private Long movieCode;
    private String title;
    private LocalDate releaseDate;
    private List<Integer> genres;
    private Float popularity;
    private String overview;

    private String backdropPath;

    private boolean adult;

    private boolean video;

    private String originalLanguage;

    private Integer voteCount;

    private Double voteAverage;

    private String posterPath;

    public MovieDto(Long movieCode, String title, LocalDate releaseDate, Float popularity, String overview, String backdropPath, boolean adult, boolean video, String originalLanguage, Integer voteCount, String posterPath) {
        this.movieCode = movieCode;
        this.title = title;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.overview = overview;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.video = video;
        this.originalLanguage = originalLanguage;
        this.voteCount = voteCount;
        this.posterPath = posterPath;
    }
}
