package com.anhngo.nhaichuttruyen.repository;

import com.anhngo.nhaichuttruyen.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    boolean existsByUsername(String username);
}
