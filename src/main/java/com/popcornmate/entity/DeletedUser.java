package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "deleted_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class DeletedUser {

    @Id
    @Column(name = "user_code")
    private Integer userCode;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_code", referencedColumnName = "user_code", insertable = false, updatable = false)
    private User user;

    private LocalDateTime deletedAt;
    private String reason;
}
