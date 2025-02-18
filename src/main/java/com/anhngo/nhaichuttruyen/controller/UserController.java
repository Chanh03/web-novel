package com.anhngo.nhaichuttruyen.controller;

import com.anhngo.nhaichuttruyen.entity.Users;
import com.anhngo.nhaichuttruyen.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/api")
public class UserController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> test() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> test(@PathVariable UUID id) {
        return ResponseEntity.ok(usersService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Users users) {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi không xác định!");
        }
        if (usersService.existsByUsername(users.getUsername())) {
            return ResponseEntity.badRequest().body("Tên người dùng đã tồn tại!");
        }
        users.setCreateDate(LocalDateTime.now());
        users.setUpdateDate(LocalDateTime.now());
        usersService.save(users);
        return ResponseEntity.ok().body("Thêm người dùng thành công!");
    }
}