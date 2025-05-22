package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    @Column(name = "movie_code")
    private Long movieCode;
    @Column(name = "title")
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String overview;
    @Column(name = "release_date")
    private LocalDate releaseDate;
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
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Genre> genres = new ArrayList<>();

    public Movie(Long movieCode, String title, String overview, String  releaseDate, float popularity, String backdropPath, String originalLanguage, String posterPath, boolean isVideo, boolean isAdult, float voteAverage, int voteCount, List<Genre> genres) {
        this.movieCode = movieCode;
        this.title = title;
        this.overview = overview;
        this.releaseDate = LocalDate.parse(releaseDate);
        this.popularity = popularity;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.posterPath = posterPath;
        this.isVideo = isVideo;
        this.isAdult = isAdult;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.genres = genres;
    }
}
