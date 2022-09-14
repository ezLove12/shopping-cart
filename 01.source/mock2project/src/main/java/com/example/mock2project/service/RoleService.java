package com.example.mock2project.service;

import com.example.mock2project.Entity.Role;
import com.example.mock2project.Entity.User;
import com.example.mock2project.repository.RoleRepository;
import com.example.mock2project.repository.UserRepository;
import com.example.mock2project.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PreRemove;
import java.util.List;

@Service
@Slf4j
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    static private UserRepository userRepository;
    @Autowired
    public void init(UserRepository userRepository){
        RoleService.userRepository = userRepository;
        log.info("Intializing user repository");
    }
    public void deleteRole(Long id)  {

        roleRepository.deleteById(id);

    }

    @PreRemove
    private void removeRoleFromUser(Role role){
        List<User> user = userRepository.findAll();
        for (User u: user){
            u.getRoles().remove(role);
            log.info("delete role");
        }
    }
}
