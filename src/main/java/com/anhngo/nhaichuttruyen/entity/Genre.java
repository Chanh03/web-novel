package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "genres")
public class Genre {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<GenreNovel> genreGenreNovels;

}
