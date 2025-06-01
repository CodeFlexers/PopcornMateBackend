package com.popcornmate.recommender.dto;

import lombok.Data;

@Data
public class UserActivityGenresDto {

    private Integer genreCode;
    private String genreName; // 있어도 없어도
    private Long genreCount;
    private Long timeCount;

    //    생성자 이름이 틀리거나 Lombok @AllArgsConstructor로 생성되어 있어도 JPQL new 구문은 명시적 생성자만 인식함

    public UserActivityGenresDto(Integer genreCode, String genreName, Long genreCount, Long timeCount) {
        this.genreCode = genreCode;
        this.genreName = genreName;
        this.genreCount = genreCount;
        this.timeCount = timeCount;
    }

}
