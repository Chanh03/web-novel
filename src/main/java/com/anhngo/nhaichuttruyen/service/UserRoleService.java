package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRoleService {
    List<UserRole> getAllUserRoles();

    void save(UserRole userRole);

    boolean isExisted(Integer userId, Integer roleId);
}
