package com.example.mock2project.repository;

import com.example.mock2project.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    void deleteById(Long id);
}
