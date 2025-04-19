package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "ranks")
public class Rank {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime createDate;

    @OneToMany(mappedBy = "rank")
    private Set<UserRank> rankUserRanks;

}
