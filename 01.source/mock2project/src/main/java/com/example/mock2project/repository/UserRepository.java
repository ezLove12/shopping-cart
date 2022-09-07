package com.example.mock2project.repository;

import com.example.mock2project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update  User u set u.status = true where u.email=?1")
    int updateStatus(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update user_role set role_id=1 where user_id=?1")
    int updateRole(Long id);
}
