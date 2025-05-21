package com.popcornmate.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genre")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Genre {

    @EmbeddedId
    private GenreId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieCode")
    @JoinColumn(name = "movie_code")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("genreNameCode")
    @JoinColumn(name = "genre_name_code")
    private GenreName genreName;

}
