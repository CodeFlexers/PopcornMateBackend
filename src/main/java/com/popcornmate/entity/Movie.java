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
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_code")
    private Long movieCode;
    @Column(name = "title")
    private String title;
    @Column(name = "overview")
    private String overview;
    @Column(name = "release_date")
    private LocalDateTime releaseDate;
    @Column(name = "popularity")
    private float popularity;
    @Column(name = "backdrop_path")
    private String backdropPath;
    @Column(name = "original_language")
    private String originalLanguage;
    @Column(name = "poster_path")
    private String posterPath;
    @Column(name = "is_video")
    private boolean isVideo;
    @Column(name = "is_adult")
    private boolean isAdult;
    @Column(name = "vote_average")
    private float voteAverage;
    @Column(name = "vote_count")
    private int voteCount;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Review> reviews = new ArrayList<>();
}
