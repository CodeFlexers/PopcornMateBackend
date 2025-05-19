package com.popcornmate.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deleted_user")
public class DeletedUser {

    @Id
    @Column(name = "user_code")
    private Long userCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", referencedColumnName = "user_code", insertable = false, updatable = false)
    private User user;

    private LocalDateTime deletedAt;
    private String reason;
}
