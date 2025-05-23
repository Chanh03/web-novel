package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Entity
@Getter
@Setter
@Table(name = "images")
public class Image {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(nullable = false, length = 100)
    private String url;

    @Column(nullable = false)
    private Integer orderNum;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

}
