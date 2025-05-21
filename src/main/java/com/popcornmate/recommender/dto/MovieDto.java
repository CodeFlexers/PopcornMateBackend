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

}
