package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> roleUserRoles;

}
