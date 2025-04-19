package com.anhngo.nhaichuttruyen.controller;

import com.anhngo.nhaichuttruyen.DTO.JwtResponse;
import com.anhngo.nhaichuttruyen.DTO.LoginRequest;
import com.anhngo.nhaichuttruyen.DTO.RegisterRequest;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.entity.UserRole;
import com.anhngo.nhaichuttruyen.repos.UserRepository;
import com.anhngo.nhaichuttruyen.repos.UserRoleRepository;
import com.anhngo.nhaichuttruyen.service.RoleService;
import com.anhngo.nhaichuttruyen.service.UserService;
import com.anhngo.nhaichuttruyen.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final UserRoleRepository userRoleRepo;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil,
                          UserRepository userRepo, UserService userService, PasswordEncoder encoder,
                          RoleService roleService, UserRoleRepository userRoleRepo) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.encoder = encoder;
        this.roleService = roleService;
        this.userRoleRepo = userRoleRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userService.findByUsername(req.getUsername()).isPresent())
            return ResponseEntity.badRequest().body("Thông tin đăng ký đã tồn tại");

        if (req.getEmail() == null || !req.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            return ResponseEntity.badRequest().body("Email không hợp lệ");

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setWalletBalance(0);
        user.setAvatar("user.webp");
        user.setEnabled(true);
        userService.save(user);

        UserRole role = new UserRole();
        role.setUser(user);
        role.setRole(roleService.findRoleById("ROLE_USER"));
        userRoleRepo.save(role);

        return ResponseEntity.ok("Đăng ký thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai lầm rồi bạn trẻ");
        }
    }

    @GetMapping("/admin/test")
    public ResponseEntity<?> adminTest() {
        return ResponseEntity.ok("Admin access granted");
    }

    @GetMapping("/user/test")
    public ResponseEntity<?> userTest() {
        return ResponseEntity.ok("User access granted");
    }
}

