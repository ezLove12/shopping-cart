package com.example.mock2project.controller;

import com.example.mock2project.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderDetailsController {
    @Autowired
    OrderDetailsService service;
    @GetMapping("/orderdetails/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SALE_ADMIN', 'ROLE_SYSTEM_ADMIN')")
    public ResponseEntity<Map<String, Object>> getOrderDetailsByOrder(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(service.findOrderDetailsByOrderId(id), HttpStatus.OK);
    }
}
