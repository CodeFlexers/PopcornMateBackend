package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_code")
    private Integer reviewCommentCode;
    @Column(name = "content")
    private String content;
    @Column(name = "wrote_at")
    private LocalDateTime wroteAt;
    @Column(name = "is_edit")
    private boolean isEdit;


    @OneToOne
    @JoinColumn(name = "user_code")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_code")
    private Review review;

    public ReviewComment(String content, boolean isEdit, LocalDateTime wroteAt, User user, Review review) {
        this.content = content;
        this.isEdit = isEdit;
        this.wroteAt = wroteAt;
        this.user = user;
        this.review = review;
    }
}
