package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.RoleDTO;
import com.anhngo.nhaichuttruyen.entity.Role;
import com.anhngo.nhaichuttruyen.entity.UserRole;
import com.anhngo.nhaichuttruyen.repos.RoleRepository;
import com.anhngo.nhaichuttruyen.repos.UserRoleRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public RoleService(final RoleRepository roleRepository,
                       final UserRoleRepository userRoleRepository) {
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public List<RoleDTO> findAll() {
        final List<Role> roles = roleRepository.findAll(Sort.by("id"));
        return roles.stream()
                .map(role -> mapToDTO(role, new RoleDTO()))
                .toList();
    }

    public RoleDTO get(final String id) {
        return roleRepository.findById(id)
                .map(role -> mapToDTO(role, new RoleDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final RoleDTO roleDTO) {
        final Role role = new Role();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getId();
    }

    public void update(final String id, final RoleDTO roleDTO) {
        final Role role = roleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    public void delete(final String id) {
        roleRepository.deleteById(id);
    }

    private RoleDTO mapToDTO(final Role role, final RoleDTO roleDTO) {
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
        role.setName(roleDTO.getName());
        return role;
    }

    public ReferencedWarning getReferencedWarning(final String id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Role role = roleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final UserRole roleUserRole = userRoleRepository.findFirstByRole(role);
        if (roleUserRole != null) {
            referencedWarning.setKey("role.userRole.role.referenced");
            referencedWarning.addParam(roleUserRole.getId());
            return referencedWarning;
        }
        return null;
    }

}
