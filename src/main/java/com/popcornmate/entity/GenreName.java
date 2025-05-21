package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genre_name")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GenreName {

    @Id
    @Column(name = "genre_name_code")
    private Integer genreNameCode;

    @Column
    private String name;

}
