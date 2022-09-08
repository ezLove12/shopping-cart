package com.example.mock2project.controller;

import com.example.mock2project.service.JWTService;
import com.example.mock2project.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user/profile/")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;

    @PostMapping("/changepassword")
    public String changePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "hello";
    }
}
