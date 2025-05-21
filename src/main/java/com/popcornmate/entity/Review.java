package com.popcornmate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_code")
    private Movie movie;
    @OneToOne
    @JoinColumn(name = "user_code")
    private User user;
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewComment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewReaction> reactionList = new ArrayList<>();
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReportedReview> reportedList = new ArrayList<>();

    public Review(String content, float rate, Movie movie, LocalDateTime wroteAt, boolean isEdit, User user) {
        this.content = content;
        this.rate = rate;
        this.movie = movie;
        this.wroteAt = wroteAt;
        this.isEdit = isEdit;
        this.user = user;
    }
}
