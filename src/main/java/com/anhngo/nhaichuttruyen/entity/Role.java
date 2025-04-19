package com.anhngo.nhaichuttruyen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> roleUserRoles;
}
