package com.example.mock2project.controller;


import com.example.mock2project.requestEntity.AddedRole;
import com.example.mock2project.service.OrderService;
import com.example.mock2project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> findAll(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "3") int size) throws Exception {
        return new ResponseEntity<>(userService.findAllUser(page, size), HttpStatus.OK);
    }



    @PostMapping("/role/addrole")
    public ResponseEntity<String> addRoleToUser(@RequestParam Long id, @RequestBody AddedRole addedRole) throws Exception {
        userService.addRoleToUser(id, addedRole);
        return new ResponseEntity<>("Add Role Successfully", HttpStatus.OK);
    }

//    @GetMapping("/orders")
//    public ResponseEntity<Map<String, Object>> findAllOrder(@RequestParam(defaultValue = "0") int page,
//                                                            @RequestParam(defaultValue = "3") int size) throws Exception {
//        return new ResponseEntity<>(orderService.findAllOrder(page, size), HttpStatus.OK);
//    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> searchUser(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "3") int size,
                                                          @RequestParam(defaultValue = "") String search) throws Exception{
        return new ResponseEntity<>(userService.searchUser(search, page, size), HttpStatus.OK);
    }
}
