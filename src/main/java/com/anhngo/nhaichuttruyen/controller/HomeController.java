package com.anhngo.nhaichuttruyen.controller;

import com.anhngo.nhaichuttruyen.entity.Users;
import com.anhngo.nhaichuttruyen.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.status(404).body("Welcome to NhaiChutTruyen API");
    }

    @GetMapping("/test")
    public ResponseEntity<List<Users>> test() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Users users) {
        try {
            Users savedUser = usersService.save(users);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi thêm người dùng!");
        }
    }
}
