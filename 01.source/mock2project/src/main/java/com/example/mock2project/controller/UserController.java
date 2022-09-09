package com.example.mock2project.controller;

import com.example.mock2project.Entity.Order;
import com.example.mock2project.service.JWTService;
import com.example.mock2project.service.OrderService;
import com.example.mock2project.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user/profile/")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;

    @Autowired
    OrderService orderService;
    @PostMapping("/changepassword")
    public ResponseEntity changePassword(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) throws Exception {
        Long userTokenId = Long.valueOf(jwtService.getUserIdFromToken(request, response));
        if(userTokenId == id){
            String new_password = request.getParameter("new_password");
            String ole_password = request.getParameter("old_password");
            userService.changePassword(new_password,id, ole_password);
            return ResponseEntity.ok("Change password success");
        }else{
            throw new AccessDeniedException("You don't have permission");
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getUserOrder(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response){
        Long userTokenId = Long.valueOf(jwtService.getUserIdFromToken(request, response));
        if(userTokenId == id){
            return new ResponseEntity<>(orderService.getOrdersByUserId(id), HttpStatus.OK);
        }else{
            throw new AccessDeniedException("You don't have permission");
        }
    }

    @GetMapping("/total")
    public ResponseEntity<String> getPurchasepriceByUserId(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response){
        Long userTokenId = Long.valueOf(jwtService.getUserIdFromToken(request, response));
        if(userTokenId == id){
            return new ResponseEntity<>(orderService.getPurchasePriceByUserId(id), HttpStatus.OK);
        }else{
            throw new AccessDeniedException("You don't have permission");
        }
    }
}

