package com.example.mock2project.service;

import com.example.mock2project.Entity.Role;
import com.example.mock2project.Entity.SignInToken;
import com.example.mock2project.Entity.User;
import com.example.mock2project.dto.UserDTO;
import com.example.mock2project.repository.RoleRepository;
import com.example.mock2project.repository.UserRepository;
import com.example.mock2project.requestEntity.AddedRole;
import com.example.mock2project.security.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else{
            log.info("User found "+username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            log.info(role.getName());
        });

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), authorities);
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
        roles.add(new Role(3L,"USER"));
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

    public User getUser(String username){
        return userRepository.findByEmail(username);
    }

    public void changePassword(String new_password, Long user_id, String old_password) throws Exception {
        String encodedPassword = userRepository.getEncodedPassword(user_id);
        log.info(encodedPassword);
        log.info(old_password);
        if (passwordEncoder.bCryptPasswordEncoder().matches(old_password,encodedPassword)){
            userRepository.changePassword(passwordEncoder.bCryptPasswordEncoder().encode(new_password), user_id);

        }else{
            throw new Exception("Password must matches old password");
        }
    }

    public Map<String, Object> findAllUser(int page, int size) throws Exception {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<UserDTO> listUserDTO = new ArrayList<>();
            Page<User> pageUsers = userRepository.findAll(paging);
            pageUsers.getContent().forEach(user -> {
                listUserDTO.add(new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.isStatus(), user.getRoles()));
            });
            Map<String, Object> response = new HashMap<>();
            response.put("curPage", pageUsers.getNumber());
            response.put("users", listUserDTO);
            response.put("totalPage", pageUsers.getTotalPages());
            return response;
        }catch (Exception e){
            throw new Exception();
        }
    }

    public void addRoleToUser(Long id, AddedRole addedRole) throws Exception {
        User u = userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));

        Set<String> listRole = addedRole.getRoles();
        Set<Role> roles = u.getRoles();

        listRole.forEach(role -> {
            switch (role){
                case "ROLE_SYSTEM_ADMIN" ->{
                    Role roleSysAdmin = roleRepository.findByName("ROLE_SYSTEM_ADMIN");
                    roles.add(roleSysAdmin);
                }

                case "ROLE_SALE_ADMIN" ->{
                    Role roleSaleAdmin = roleRepository.findByName("ROLE_SALE_ADMIN");
                    roles.add(roleSaleAdmin);
                }
            }
        });

        u.setRoles(roles);
        userRepository.save(u);
    }

    public Map<String, Object> searchUser(String search,int page,int size) throws Exception {
        try{
            Pageable paging = PageRequest.of(page, size);
            List<UserDTO> userDTOList = new ArrayList<>();
            Page<User> pageUsers = userRepository.findUserByField("%"+search+"%", paging);
            pageUsers.getContent().forEach(user -> {
                userDTOList.add(new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.isStatus(), user.getRoles()));
            });

            Map<String, Object> response = new HashMap<>();
            response.put("curPage", pageUsers.getNumber());
            response.put("listUsers", userDTOList);
            response.put("totalUser", pageUsers.getTotalElements());
            response.put("totalPage", pageUsers.getTotalPages());
            return response;
        }catch (Exception ex){
            throw new Exception();
        }
    }
}
