package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, String> {
}
