package com.popcornmate.recommender.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieDto {

    private Long movieCode;
    private String title;
    private LocalDateTime releaseDate;
    private List<GenreDto> genreList;
    private Float popularity;

}
