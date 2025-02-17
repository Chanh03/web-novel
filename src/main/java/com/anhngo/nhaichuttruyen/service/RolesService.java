package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.entity.Roles;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolesService {
    List<Roles> getAllRoles();

    void save(@Valid Roles roles);
}
