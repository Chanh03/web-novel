package com.anhngo.nhaichuttruyen.rest;

import com.anhngo.nhaichuttruyen.DTO.UserRoleDTO;
import com.anhngo.nhaichuttruyen.service.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/userRoles", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRoleRest {

    private final UserRoleService userRoleService;

    public UserRoleRest(final UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> getAllUserRoles() {
        return ResponseEntity.ok(userRoleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserRoleDTO>> getUserRole(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(userRoleService.getAllByUserId(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createUserRole(
            @RequestBody @Valid final UserRoleDTO userRoleDTO) {
        final Integer createdId = userRoleService.create(userRoleDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateUserRole(@PathVariable(name = "id") final Integer id,
                                                  @RequestBody @Valid final UserRoleDTO userRoleDTO) {
        userRoleService.update(id, userRoleDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable(name = "id") final Integer id) {
        userRoleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}