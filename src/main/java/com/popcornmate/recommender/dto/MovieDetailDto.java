package com.popcornmate.recommender.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MovieDetailDto {
    private Long movieCode;
    private String title;
    private LocalDate releaseDate;
    private List<String> genres;
    private Float popularity;
    private String overview;

    private String backdropPath;

    private boolean adult;

    private boolean video;

    private String originalLanguage;

    private Integer voteCount;

    private Double voteAverage;

    private String posterPath;

}
