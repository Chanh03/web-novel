package com.anhngo.nhaichuttruyen.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(nullable = false, length = 100)
    @JsonProperty("username")
    private String username;

    @Column(nullable = false)
    @JsonProperty("fullName")
    private String fullName;

    @Column(nullable = false)
    @JsonProperty("password")
    private String password;

    @Column(nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(nullable = false, length = 100)
    @JsonProperty("avatar")
    private String avatar;

    @Column(nullable = false)
    @JsonProperty("isEnabled")
    private Boolean isEnabled;

    @Column(nullable = false)
    @JsonProperty("createDate")
    private LocalDateTime createDate;

    @Column(nullable = false)
    @JsonProperty("walletBalance")
    private Integer walletBalance;

    @Column(nullable = false)
    @JsonProperty("updateDate")
    private LocalDateTime updateDate;
}