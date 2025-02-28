package com.anhngo.nhaichuttruyen.implement;

import com.anhngo.nhaichuttruyen.entity.Roles;
import com.anhngo.nhaichuttruyen.repository.RolesRepository;
import com.anhngo.nhaichuttruyen.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImplement implements RolesService {
    @Autowired
    private RolesRepository roleRepository;

    @Override
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Roles roles) {
        roleRepository.save(roles);
    }

    @Override
    public Roles findById(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }
}
