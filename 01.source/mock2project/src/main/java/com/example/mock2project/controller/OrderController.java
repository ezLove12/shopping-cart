package com.example.mock2project.controller;

import com.example.mock2project.Entity.Order;
import com.example.mock2project.dto.OrderDTO;
import com.example.mock2project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/order/{id}/update")
    @PreAuthorize("hasAuthority('ROLE_SALE_ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam int status) throws Exception {
        orderService.updateOrderStatus(id, status);
        return new ResponseEntity<>("Update status for order with id = "+id+" success", HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SALE_ADMIN', 'ROLE_SYSTEM_ADMIN')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) throws Exception{
        OrderDTO o = orderService.findOrderById(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN', 'ROLE_SALE_ADMIN')")
    public ResponseEntity<Map<String, Object>> sortOrderByDate(@RequestParam(defaultValue = "desc") String sort,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "3") int size) throws Exception{
        return new ResponseEntity<>(orderService.sortOrderByOrderDate(page,size, sort), HttpStatus.OK);
    }
}
