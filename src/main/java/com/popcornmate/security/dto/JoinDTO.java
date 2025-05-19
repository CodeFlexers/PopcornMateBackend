package com.popcornmate.security.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {
    private String id;
    private String password;
    private String email;
    private String nickname;
}
