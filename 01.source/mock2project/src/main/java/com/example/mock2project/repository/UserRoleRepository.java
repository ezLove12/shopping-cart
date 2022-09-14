package com.example.mock2project.repository;

import com.example.mock2project.Entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository {
    void deleteRoleFromUser(Role role);
}
