package com.anhngo.nhaichuttruyen.controller;

import com.anhngo.nhaichuttruyen.entity.Users;
import com.anhngo.nhaichuttruyen.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    private final UsersService usersService;

    public HomeController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.status(404).body("Welcome to NhaiChutTruyen API");
    }

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