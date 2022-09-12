package com.example.mock2project.service;


import com.example.mock2project.Entity.Order;
import com.example.mock2project.Entity.OrderDetails;
import com.example.mock2project.dto.OrderDetailsDTO;
import com.example.mock2project.repository.OrderDetailsRepository;

import com.example.mock2project.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderDetailsService {
    @Autowired
    OrderDetailsRepository repo;
    @Autowired
    OrderRepository orderRepo;
    public List<OrderDetailsDTO> findOrderDetailsByOrderId(Long id) throws Exception {
        Order o = orderRepo.findById(id).orElseThrow(()->new Exception("Not found Order with Id: "+ id));

            List<OrderDetails> list = repo.findByOrder(id);
            List<OrderDetailsDTO> listOdDTO = new ArrayList<>();
            list.forEach(od -> {
                listOdDTO.add(new OrderDetailsDTO(od.getId(), od.getProductQuantity(), od.getProductTotalPrice(), od.getOrder().getId(), od.getProduct().getId()));
            });
            return listOdDTO;
    }
}
