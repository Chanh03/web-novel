package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
