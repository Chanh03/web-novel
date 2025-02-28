package com.anhngo.nhaichuttruyen.controller;

import com.anhngo.nhaichuttruyen.entity.Roles;
import com.anhngo.nhaichuttruyen.entity.Users;
import com.anhngo.nhaichuttruyen.service.RolesService;
import com.anhngo.nhaichuttruyen.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RolesService rolesService;

    @GetMapping
    public ResponseEntity<List<Roles>> test() {
        return ResponseEntity.ok(rolesService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Roles roles) {
        rolesService.save(roles);
        return ResponseEntity.ok().body("Thêm vai trò thành công!");
    }
}