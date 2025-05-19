package com.popcornmate.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userCode;
    private String id;
    private String email;
    private String profileImage;
    private LocalDateTime lastLoginTime;
    private String nickname;

    public UserDto(Integer userCode, String nickname) {
        this.userCode = userCode;
        this.nickname = nickname;
    }
}
