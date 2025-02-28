package com.anhngo.nhaichuttruyen.repository;

import com.anhngo.nhaichuttruyen.entity.Roles;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {
}
