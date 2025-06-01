package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "movie_history")
public class MovieHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_history_code")
    private Integer movieHistoryCode;

    @JoinColumn(name = "movie_code")
    @ManyToOne
    private Movie movie;

    @JoinColumn(name = "user_code")
//    @ManyToOne
    private Integer userCode;

    @Column(name = "like_score", columnDefinition = "INT DEFAULT 4")
    private int likeScore;

    public MovieHistory(Integer userCode, Movie movie) {
        this.movie = movie;
        this.userCode = userCode;
    }
}
