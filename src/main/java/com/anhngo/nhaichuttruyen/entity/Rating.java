package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Entity
@Getter
@Setter
@Table(name = "ratings")
public class Rating {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false, columnDefinition = "varchar(max)")
    private String comment;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime createDate;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

}
