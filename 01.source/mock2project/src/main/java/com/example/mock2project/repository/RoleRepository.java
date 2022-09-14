package com.example.mock2project.repository;

import com.example.mock2project.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    @Transactional
    @Modifying
    void deleteById(Long id);
}
