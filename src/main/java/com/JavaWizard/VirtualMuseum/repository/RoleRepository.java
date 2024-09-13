package com.JavaWizard.VirtualMuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JavaWizard.VirtualMuseum.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}