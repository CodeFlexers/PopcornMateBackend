package com.popcornmate.recommender.dto;

import lombok.Data;

@Data
public class MovieHistoryGenresDto {

    private Integer genreCode;
    private String genreName; // 있어도 없어도
    private Long genreCount;
    private int likeScore;


    public MovieHistoryGenresDto(Integer genreCode, String genreName, Long genreCount) {
        this.genreCode = genreCode;
        this.genreName = genreName;
        this.genreCount = genreCount;
    }

}
