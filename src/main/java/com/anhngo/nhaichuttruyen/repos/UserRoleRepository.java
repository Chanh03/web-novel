package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Role;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findFirstByRole(Role role);

    UserRole findFirstByUser(User user);

    void deleteByUserId(UUID id);

    List<UserRole> findByUserId(UUID id);
}
