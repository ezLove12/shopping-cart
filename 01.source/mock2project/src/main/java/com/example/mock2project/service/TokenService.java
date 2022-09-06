package com.example.mock2project.service;

import com.example.mock2project.Entity.SignInToken;
import com.example.mock2project.repository.SignInTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {
    @Autowired
    SignInTokenRepository repo;

    public void saveToken(SignInToken token){
        repo.save(token);
    }

    public Optional<SignInToken> getToken(String token){
        return repo.findByToken(token);
    }
}
