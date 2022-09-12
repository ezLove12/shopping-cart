package com.example.mock2project.service;

import com.example.mock2project.Entity.Order;
import com.example.mock2project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> findAllOrder(int page, int size) throws Exception {
        Pageable paging = PageRequest.of(page, size);
        Page<Order> pageOrder = orderRepository.findAll(paging);
        try{
            Map<String, Object> response = new HashMap<>();
            response.put("curPage", pageOrder.getNumber());
            response.put("orders", pageOrder.getContent());
            response.put("totalPage", pageOrder.getTotalPages());
            return response;
        }catch (Exception e){
            throw new Exception("Some thing went wrong");
        }
    }
}
