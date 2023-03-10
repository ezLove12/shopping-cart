package com.example.mock2project.controller;

import com.example.mock2project.Entity.User;
import com.example.mock2project.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    RegistrationService service;

    @PostMapping( value="/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody User user){
        return service.register(user);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam(name = "token") String token){
        return service.confirmToken(token);
    }
}
