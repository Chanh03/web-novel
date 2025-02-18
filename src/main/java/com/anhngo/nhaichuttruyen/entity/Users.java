package com.anhngo.nhaichuttruyen.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "users")
public class Users {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 100)
    @NotNull
    @Column(name = "avatar", nullable = false, length = 100)
    private String avatar;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = false;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "wallet_balance", nullable = false)
    private Integer walletBalance;

    @NotNull
    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles = new LinkedHashSet<>();
}