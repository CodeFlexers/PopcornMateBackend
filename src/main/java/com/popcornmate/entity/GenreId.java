package com.popcornmate.entity;


import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class GenreId implements Serializable {

    private Long movieCode;

    private Integer genreNameCode;

}
