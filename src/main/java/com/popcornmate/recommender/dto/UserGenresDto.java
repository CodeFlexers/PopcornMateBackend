package com.popcornmate.recommender.dto;

import lombok.Data;

@Data
public class UserGenresDto {

    private Integer genreCode;
    private String name;
    private Long genreCount;
    private Long wait;

    public UserGenresDto(Integer genreCode, String name, Long genreCount, Long wait) {
        this.genreCode = genreCode;
        this.name = name;
        this.genreCount = genreCount;
        this.wait = wait;
    }
}
