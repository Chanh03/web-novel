package com.anhngo.nhaichuttruyen.repository;

import com.anhngo.nhaichuttruyen.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByUserIdAndRoleId(Integer userId, Integer roleId);
}
