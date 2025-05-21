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

    @JoinColumn(name = "movie_code")
//    @ManyToOne
    private Long movieCode;

    @JoinColumn(name = "user_code")
//    @ManyToOne
    private Integer userCode;

    @Column(name = "time_on_page")
    private int timeOnPage;

    public UserActivity(Long movieCode, Integer userCode, int timeOnPage) {
        this.movieCode = movieCode;
        this.userCode = userCode;
        this.timeOnPage = timeOnPage;
    }
}
