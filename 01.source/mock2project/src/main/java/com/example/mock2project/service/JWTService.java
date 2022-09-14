package com.example.mock2project.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.mock2project.Entity.Role;
import com.example.mock2project.Entity.User;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Slf4j
public class JWTService {
    private final UserService userService;

    public JWTService(UserService userService) {
        this.userService = userService;
    }

    public Map<String, String> getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refreshToken = authorizationHeader.substring("Bearer ".length()); //get token after bearer
                Algorithm algorithm = Algorithm.HMAC256("viet".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000)) //10m
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access token", accessToken);
                tokens.put("refresh token", refreshToken);
                return tokens;

            }catch (JwtException e){
                response.setHeader("error", e.getMessage());
                response.setStatus(403);
                Map<String, String> error = new HashMap<>();
                error.put("error", e.getMessage());
                return error;
            }

        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }

    public String getUserIdFromToken(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            try {
                String accessToken = authorizationHeader.substring("Bearer ".length()); //get token after bearer
                log.info(accessToken);
                Algorithm algorithm = Algorithm.HMAC256("viet".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(accessToken);
                String username = decodedJWT.getSubject();
                log.info(username);
                User user = userService.getUser(username);
                return String.valueOf(user.getId());
            }catch (Exception e ){
                return e.getMessage();
            }
        }else{
            throw new RuntimeException("Your don't have permission to do that");
        }
    }
}
