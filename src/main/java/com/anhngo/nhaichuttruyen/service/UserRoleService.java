package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserRoleService {
    List<UserRole> getAllUserRoles();

    void save(UserRole userRole);

    boolean isExisted(UUID userId, String roleId);
}
