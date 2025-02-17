package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    List<Users> getAllUsers();

    void save(Users users);

    Users findById(Integer userId);
}
