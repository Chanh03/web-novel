package com.anhngo.nhaichuttruyen.implement;

import com.anhngo.nhaichuttruyen.entity.UserRole;
import com.anhngo.nhaichuttruyen.repository.UserRoleRepository;
import com.anhngo.nhaichuttruyen.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImplement implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public boolean isExisted(UUID userId, Integer roleId) {
        return userRoleRepository.findByUserIdAndRoleId(userId, roleId) != null;
    }
}
