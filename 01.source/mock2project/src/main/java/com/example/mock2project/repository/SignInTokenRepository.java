package com.example.mock2project.repository;

import com.example.mock2project.Entity.SignInToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignInTokenRepository extends JpaRepository<SignInToken, Long> {
    Optional<SignInToken> findByToken(String token);
}
