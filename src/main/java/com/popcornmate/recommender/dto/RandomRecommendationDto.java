package com.popcornmate.recommender.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RandomRecommendationDto {

    private Long movieCode;
    private String title;
    private LocalDate releaseDate;
    private List<String> genreNames;
    private Float popularity;
    private String overview;

    private String backdropPath;

    private boolean adult;

    private boolean video;

    private String originalLanguage;

    private Integer voteCount;

    private float voteAverage;

    private String posterPath;
}
