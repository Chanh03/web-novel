package com.anhngo.nhaichuttruyen.controller;

import com.anhngo.nhaichuttruyen.entity.Roles;
import com.anhngo.nhaichuttruyen.entity.UserRole;
import com.anhngo.nhaichuttruyen.entity.Users;
import com.anhngo.nhaichuttruyen.service.RolesService;
import com.anhngo.nhaichuttruyen.service.UserRoleService;
import com.anhngo.nhaichuttruyen.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public ResponseEntity<List<UserRole>> test() {
        return ResponseEntity.ok(userRoleService.getAllUserRoles());
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestParam UUID userId, @RequestParam String roleId) {
        // Check if user exists
        if (userRoleService.isExisted(userId, roleId)) {
            return ResponseEntity.badRequest().body("Vai trò người dùng đã tồn tại!");
        }
        UserRole userRole = new UserRole();
        userRole.setRole(rolesService.findById(roleId));
        userRole.setUser(usersService.findById(userId));
        userRoleService.save(userRole);
        return ResponseEntity.ok("Thêm vai trò người dùng thành công!");
    }
}
