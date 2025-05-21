package com.popcornmate.review.dto;

import java.time.LocalDateTime;

public record ReviewDto(
        Integer reviewCode,
        String content,
        float rate,
        LocalDateTime wroteAt,
        boolean isEdit,
        Long movieCode,
        String userNickname,
        String profileImage
) {

}
