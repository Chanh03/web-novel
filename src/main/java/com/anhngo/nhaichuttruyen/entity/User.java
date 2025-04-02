package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {

    @Id
    @Column(
            nullable = false,
            updatable = false,
            columnDefinition = "uniqueidentifier"
    )
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 100)
    private String avatar;

    @Column(nullable = false)
    private Boolean isEnabled;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime createDate;

    @Column(nullable = false)
    private Integer walletBalance;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime updateDate;

    @OneToMany(mappedBy = "author")
    private Set<Novel> authorNovels;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> userTransactions;

    @OneToMany(mappedBy = "user")
    private Set<UserRank> userUserRanks;

    @OneToMany(mappedBy = "user")
    private Set<PersonalNotification> userPersonalNotifications;

    @OneToMany(mappedBy = "user")
    private Set<ReceiveNotification> userReceiveNotifications;

    @OneToMany(mappedBy = "user")
    private Set<Bookmark> userBookmarks;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userUserRoles;

    @OneToMany(mappedBy = "user")
    private Set<Favorite> userFavorites;

    @OneToMany(mappedBy = "user")
    private Set<Purchase> userPurchases;

}
