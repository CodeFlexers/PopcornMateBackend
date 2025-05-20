package com.popcornmate.review.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class ReviewCreateDto {
    private String content;
    private float rate;
    private Long movieCode;
}
