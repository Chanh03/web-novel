package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Users")
@Getter
@Setter
public class User implements UserDetails {
    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userUserRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getName())).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

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
    private Boolean enabled;

    @CreatedDate
    @Column(nullable = false, columnDefinition = "datetime2")
    private LocalDateTime createDate;

    @Column(nullable = false)
    private Integer walletBalance;

    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "datetime2")
    private LocalDateTime updateDate;

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
