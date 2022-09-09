package com.example.mock2project.service;

import com.example.mock2project.Entity.Order;
import com.example.mock2project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrdersByUserId(Long id){
        return orderRepository.findByUserId(id);
    }

    public String getPurchasePriceByUserId(Long id){
        return orderRepository.getPurchasePriceByUserId(id);
    }
}
