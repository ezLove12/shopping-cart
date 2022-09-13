package com.example.mock2project.service;


import com.example.mock2project.Entity.Order;
import com.example.mock2project.Entity.OrderDetails;
import com.example.mock2project.Entity.Product;
import com.example.mock2project.dto.ProductDTO;
import com.example.mock2project.repository.CateRepository;
import com.example.mock2project.repository.OrderDetailsRepository;

import com.example.mock2project.repository.OrderRepository;
import com.example.mock2project.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderDetailsService {
    @Autowired
    OrderDetailsRepository repo;
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    ProductRepository proRepo;
    @Autowired
    CateRepository cateRepo;
    public Map<String, Object> findOrderDetailsByOrderId(Long id) throws Exception {
        Order o = orderRepo.findById(id).orElseThrow(()->new Exception("Not found Order with Id: "+ id));

            List<OrderDetails> list = repo.findByOrder(id);
            List<ProductDTO> listProduct = new ArrayList<>();
            list.forEach(od -> {
                Product product = proRepo.findById(od.getProduct().getId()).get();
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(product.getId());
                productDTO.setPname(product.getName());
                productDTO.setQuantity(od.getProductQuantity()); //total product quantity per product
                productDTO.setPprice(od.getProductTotalPrice()); //total product price per product
                productDTO.setCate_name(cateRepo.findById(product.getCategories().getId()).get().getName());
                listProduct.add(productDTO);
            });
            Map<String, Object> response = new HashMap<>();
            response.put("order_id", o.getId());
            response.put("shipping_address", o.getAddress());
            response.put("contact_info", o.getPhone());
            response.put("order_status", o.getStatus());
            response.put("product_list", listProduct);
            return response;
    }
}
