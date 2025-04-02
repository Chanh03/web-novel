package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Role;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findFirstByRole(Role role);

    UserRole findFirstByUser(User user);

}
