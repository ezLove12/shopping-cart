package com.example.mock2project.controller;

import com.example.mock2project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("order/{id}")
    @PreAuthorize("hasAuthority('ROLE_SALE_ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam int status) throws Exception {
        orderService.updateOrderStatus(id, status);
        return new ResponseEntity<>("Update status for order with id = "+id+" success", HttpStatus.OK);
    }
}
