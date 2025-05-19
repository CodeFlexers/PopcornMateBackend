package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private Integer userCode;
    @Column(name = "id", unique = true)
    private String id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "profile_image")
    private String profileImage;
    @Column(name = "password")
    private String password;
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "nickname", unique = true)
    private String nickname;
}
