package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findRoleById(String id);
}
