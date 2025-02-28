package com.anhngo.nhaichuttruyen.controller;

import com.anhngo.nhaichuttruyen.entity.UserRole;
import com.anhngo.nhaichuttruyen.entity.Users;
import com.anhngo.nhaichuttruyen.service.RolesService;
import com.anhngo.nhaichuttruyen.service.UserRoleService;
import com.anhngo.nhaichuttruyen.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/api")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private UserRoleService userRoleService;

    @Operation(summary = "Lấy danh sách user")
    @GetMapping
    public ResponseEntity<List<Users>> test() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @Operation(summary = "Tìm user theo id")
    @GetMapping("{id}")
    public ResponseEntity<Users> test(@PathVariable UUID id) {
        return ResponseEntity.ok(usersService.findById(id));
    }

    @Operation(summary = "Thêm user", description = "Thêm user mới, có check trùng username")
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
        // Thêm role cho user
        UserRole role = new UserRole();
        role.setRole(rolesService.findById("ROLE_USER"));
        role.setUser(users);
        userRoleService.save(role);
        return ResponseEntity.ok().body("Thêm người dùng thành công!");
    }
}