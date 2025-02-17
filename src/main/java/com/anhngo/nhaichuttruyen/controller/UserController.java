package com.anhngo.nhaichuttruyen.controller;

import com.anhngo.nhaichuttruyen.entity.Users;
import com.anhngo.nhaichuttruyen.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/api")
public class UserController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> test() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Users users) {
        usersService.save(users);
        return ResponseEntity.ok().body("Thêm người dùng thành công!");
    }
}