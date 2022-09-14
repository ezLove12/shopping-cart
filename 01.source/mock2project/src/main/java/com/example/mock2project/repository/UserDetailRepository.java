package com.example.mock2project.repository;

import com.example.mock2project.Entity.UserDetail;
import com.example.mock2project.dto.UserDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {


}
