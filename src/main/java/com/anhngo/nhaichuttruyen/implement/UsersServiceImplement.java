package com.anhngo.nhaichuttruyen.implement;

import com.anhngo.nhaichuttruyen.entity.Users;
import com.anhngo.nhaichuttruyen.repository.UsersRepository;
import com.anhngo.nhaichuttruyen.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImplement implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void save(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
    }

    @Override
    public Users findById(UUID userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }
}
