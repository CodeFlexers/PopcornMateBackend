package com.popcornmate.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genre")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "genre_code")
    private Integer genreCode;
    @Column
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_code")
    private Movie movie;

    public Genre(Integer genreCode, String name, Movie movie) {
        this.genreCode = genreCode;
        this.name = name;
        this.movie = movie;
    }


    @Override
    public String toString() {
        return "Genre(id=" + id + ", name=" + name + " )";
    }
}
