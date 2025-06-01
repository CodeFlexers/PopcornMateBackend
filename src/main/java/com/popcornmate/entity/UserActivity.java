package com.popcornmate.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "user_activity")
public class UserActivity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_activity_code")
    private Integer userActivityCode;

//    @JoinColumn(name = "movie_code")
//    private Long movieCode;

    @JoinColumn(name = "movie_code")
    @ManyToOne
    private Movie movie;

    @JoinColumn(name = "user_code")
//    @ManyToOne
    private Integer userCode;

    @Column(name = "time_on_page", columnDefinition = "INT DEFAULT 2")
    private int timeOnPage;

    public UserActivity(Movie movie, Integer userCode, int timeOnPage) {
        this.movie = movie;
        this.userCode = userCode;
        this.timeOnPage = timeOnPage;
    }
}
