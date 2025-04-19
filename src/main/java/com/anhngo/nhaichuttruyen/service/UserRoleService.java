package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.UserRoleDTO;
import com.anhngo.nhaichuttruyen.entity.Role;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.entity.UserRole;
import com.anhngo.nhaichuttruyen.repos.RoleRepository;
import com.anhngo.nhaichuttruyen.repos.UserRepository;
import com.anhngo.nhaichuttruyen.repos.UserRoleRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserRoleService(final UserRoleRepository userRoleRepository,
                           final RoleRepository roleRepository, final UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<UserRoleDTO> findAll() {
        final List<UserRole> userRoles = userRoleRepository.findAll();
        return userRoles.stream()
                .map(userRole -> mapToDTO(userRole, new UserRoleDTO()))
                .toList();
    }

    public List<UserRoleDTO> getAllByUserId(final UUID userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        if (userRoles.isEmpty()) {
            throw new NotFoundException("User roles not found");
        }
        return userRoles.stream()
                .map(userRole -> mapToDTO(userRole, new UserRoleDTO()))
                .collect(Collectors.toList());
    }


    public Integer create(final UserRoleDTO userRoleDTO) {
        final UserRole userRole = new UserRole();
        mapToEntity(userRoleDTO, userRole);
        return userRoleRepository.save(userRole).getId();
    }

    public void update(final Integer id, final UserRoleDTO userRoleDTO) {
        final UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userRoleDTO, userRole);
        userRoleRepository.save(userRole);
    }

    public void delete(final Integer id) {
        userRoleRepository.deleteById(id);
    }

    private UserRoleDTO mapToDTO(final UserRole userRole, final UserRoleDTO userRoleDTO) {
        userRoleDTO.setId(userRole.getId());
        userRoleDTO.setRole(userRole.getRole() == null ? null : userRole.getRole().getId());
        userRoleDTO.setUser(userRole.getUser() == null ? null : userRole.getUser().getId());
        return userRoleDTO;
    }

    private UserRole mapToEntity(final UserRoleDTO userRoleDTO, final UserRole userRole) {
        final Role role = userRoleDTO.getRole() == null ? null : roleRepository.findById(userRoleDTO.getRole())
                .orElseThrow(() -> new NotFoundException("role not found"));
        userRole.setRole(role);
        final User user = userRoleDTO.getUser() == null ? null : userRepository.findById(userRoleDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        userRole.setUser(user);
        return userRole;
    }

}
