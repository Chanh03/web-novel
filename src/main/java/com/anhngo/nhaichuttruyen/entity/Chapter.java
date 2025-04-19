package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "chapters")
public class Chapter {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime createDate;

    @Column(nullable = false)
    private Integer viewCount;

    @Column(nullable = false)
    private Integer discount;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id", nullable = false)
    private Novel novel;

    @OneToMany(mappedBy = "chapter")
    private Set<Image> chapterImages;

    @OneToMany(mappedBy = "chapter")
    private Set<Bookmark> chapterBookmarks;

    @OneToMany(mappedBy = "chapter")
    private Set<Rating> chapterRatings;

    @OneToMany(mappedBy = "chapter")
    private Set<Purchase> chapterPurchases;

}
