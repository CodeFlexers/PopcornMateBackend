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
//    @ManyToOne
    private Long movieCode;

    @JoinColumn(name = "user_code")
//    @ManyToOne
    private Integer userCode;

    public MovieHistory(Integer userCode, Long movieCode) {

        this.movieCode = movieCode;
        this.userCode = userCode;
    }
}
