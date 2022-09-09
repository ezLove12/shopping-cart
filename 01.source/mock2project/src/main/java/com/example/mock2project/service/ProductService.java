package com.example.mock2project.service;

import com.example.mock2project.Entity.Product;
import com.example.mock2project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Map<String, Object> getAllProduct(int page, int size) throws Exception {
        try{
            List<Product> products = new ArrayList<Product>();
            Pageable paging = PageRequest.of(page, size);
            Page<Product> pagePro = productRepository.findAll(paging);
            products = pagePro.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("products",products);
            response.put("curPage", pagePro.getNumber());
            response.put("totalPros", pagePro.getTotalElements());
            response.put("totalPages", pagePro.getTotalPages());
            return response;
        }catch (Exception e){
            throw new Exception("Something went wrong");
        }
    }
}
