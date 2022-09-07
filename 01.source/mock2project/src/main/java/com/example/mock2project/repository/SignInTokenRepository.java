package com.example.mock2project.repository;

import com.example.mock2project.Entity.SignInToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SignInTokenRepository extends JpaRepository<SignInToken, Long> {
    Optional<SignInToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE SignInToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime localDateTime);
}
