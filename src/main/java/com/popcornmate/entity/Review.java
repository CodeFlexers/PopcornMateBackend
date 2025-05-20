package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_code")
    private Integer reviewCode;
    @Column(name = "content")
    private String content;
    @Column(name = "rate")
    private float rate;
    @Column(name = "wrote_at")
    private LocalDateTime wroteAt;
    @Column(name = "is_edit")
    private boolean isEdit;

    @OneToOne
    @JoinColumn(name = "user_code")
    private User user;
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewComment> commentList = new ArrayList<>();
}
