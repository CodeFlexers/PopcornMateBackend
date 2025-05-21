package com.popcornmate.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ReviewCommentDto {
    private Integer reviewCommentCode;
    private String content;
    private LocalDateTime wroteAt;
    private boolean isEdit;
    private Integer reviewCode;
    private String userNickname;
    private String profileImage;
}
