package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Entity
@Getter
@Setter
@Table(name = "personal_notifications")
public class PersonalNotification {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "varchar(max)")
    private String text;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime createDate;

    @Column(nullable = false)
    private Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
