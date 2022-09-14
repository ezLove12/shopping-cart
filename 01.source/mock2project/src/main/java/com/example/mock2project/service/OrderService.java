package com.example.mock2project.service;

import com.example.mock2project.Entity.Order;
import com.example.mock2project.dto.OrderDTO;
import com.example.mock2project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Map<String, Object> getOrdersByUserId(Long id, int page, int size) throws Exception {
        try{
            Pageable paging = PageRequest.of(page, size);
            Page<Order> pageOrder = orderRepository.findByUserId(id,paging);
            List<OrderDTO> listOrderDTO = new ArrayList<>();
            pageOrder.getContent().forEach(order -> {
                listOrderDTO.add(new OrderDTO(order.getId(), order.getAddress(), order.getPhone(), order.getTotalPrice(), order.getUser().getId()));
            });
            Map<String, Object> response = new HashMap<>();
            response.put("curPage", pageOrder.getNumber());
            response.put("orders", listOrderDTO);
            response.put("total purchase", getPurchasePriceByUserId(id));
            response.put("totalPage", pageOrder.getTotalPages());
            return response;
        }catch (Exception e){
            throw new Exception("Some thing went wrong");
        }
    }

    public String getPurchasePriceByUserId(Long id){
        return orderRepository.getPurchasePriceByUserId(id);
    }

    public Map<String, Object> findAllOrder(int page, int size) throws Exception {

        try{
            Pageable paging = PageRequest.of(page, size);
            Page<Order> pageOrder = orderRepository.findAll(paging);
            List<OrderDTO> listOrderDTO = new ArrayList<>();
            pageOrder.getContent().forEach(order -> {
                listOrderDTO.add(new OrderDTO(order.getId(), order.getAddress(), order.getPhone(), order.getTotalPrice(), order.getUser().getId()));
            });
            Map<String, Object> response = new HashMap<>();
            response.put("curPage", pageOrder.getNumber());
            response.put("orders", listOrderDTO);
            response.put("totalPage", pageOrder.getTotalPages());
            return response;
        }catch (Exception e){
            throw new Exception("Some thing went wrong");
        }
    }

    public void updateOrderStatus(long order_id, int status) throws Exception {
        Order o = orderRepository.findById(order_id).orElseThrow(()-> new Exception("Not found Order with Id ="+ order_id));
        o.setStatus(status);
        orderRepository.save(o);
    }

    public OrderDTO findOrderById(long order_id) throws Exception {
        Order order = orderRepository.findById(order_id).orElseThrow(()-> new Exception("Not found Order with Id ="+ order_id));
        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getPhone(), order.getTotalPrice(), order.getUser().getId());
        return orderDTO;
    }
}
