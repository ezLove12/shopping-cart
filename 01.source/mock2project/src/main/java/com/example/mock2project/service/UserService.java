package com.example.mock2project.service;

import com.example.mock2project.Entity.Role;
import com.example.mock2project.Entity.SignInToken;
import com.example.mock2project.Entity.User;
import com.example.mock2project.repository.UserRepository;
import com.example.mock2project.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException(email);
        }

        boolean enabled = !user.isStatus();

        UserDetails userDetails  = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER").build();

        return userDetails;
    }

    public String signUpUser(User user){
        User userExist = userRepository.findByEmail(user.getEmail());

        if(userExist!=null){
            boolean isActive = userRepository.findByEmail(user.getEmail()).isStatus();
            if(!isActive){
                String token = UUID.randomUUID().toString();
                saveConfirmationToken(user, token);

                return token;
            }
            throw new IllegalStateException(String.format("User with email %s already exists!", user.getEmail()));
        }
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER"));
        user.setRoles(roles);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();

        saveConfirmationToken(user, token);

        return token;
    }

    private void saveConfirmationToken(User user, String token) {
        SignInToken confirmationToken = new SignInToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
        tokenService.saveToken(confirmationToken);
    }

    public int enableUser(String email){
        return userRepository.updateStatus(email);
    }

    public int changeRole(Long id){
        return userRepository.updateRole(id);
    }
}
