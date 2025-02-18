package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UsersService {
    List<Users> getAllUsers();

    void save(Users users);

    Users findById(UUID userId);

    boolean existsByUsername(String username);
}
