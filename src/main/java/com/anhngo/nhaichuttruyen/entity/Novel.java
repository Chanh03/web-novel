package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "novels")
public class Novel {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime createDate;

    @Column(nullable = false)
    private Boolean isDelete;

    @Column(nullable = false, length = 100)
    private String status;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime updateDate;

    @Column(nullable = false, columnDefinition = "varchar(max)")
    private String summary;

    @Column(nullable = false)
    private Integer viewCount;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false, length = 100)
    private String thumbnail;

    @Column(nullable = false)
    private Integer likeCount;

    @Column(nullable = false, length = 100)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "novel")
    private Set<GenreNovel> novelGenreNovels;

    @OneToMany(mappedBy = "novel")
    private Set<ReceiveNotification> novelReceiveNotifications;

    @OneToMany(mappedBy = "novel")
    private Set<Chapter> novelChapters;

    @OneToMany(mappedBy = "novel")
    private Set<Favorite> novelFavorites;

}
