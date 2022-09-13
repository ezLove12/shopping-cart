package com.example.mock2project.service;

import com.example.mock2project.Entity.Product;
import com.example.mock2project.dto.ProductDTO;
import com.example.mock2project.repository.CateRepository;
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

    @Autowired
    CateRepository cateRepository;
    public Map<String, Object> getAllProduct(int page, int size) throws Exception {
        try{
            Pageable paging = PageRequest.of(page, size);
            Page<Product> pagePro = productRepository.findAll(paging);
            List<ProductDTO> productList = new ArrayList<>();
            pagePro.getContent().forEach(product -> {
                productList.add(
                        new ProductDTO(product.getId(), product.getDescription(), product.getImage_link(),
                                product.getName(), product.getPrice(), product.getQuantity(), product.getStatus(),
                                cateRepository.findById(product.getCategories().getId()).get().getName())
                );
            });
            Map<String, Object> response = new HashMap<>();
            response.put("products",productList);
            response.put("curPage", pagePro.getNumber());
            response.put("totalPros", pagePro.getTotalElements());
            response.put("totalPages", pagePro.getTotalPages());
            return response;
        }catch (Exception e){
            throw new Exception("Something went wrong");
        }
    }

    public Map<String, Object> findProductByName(int page, int size, String name) throws Exception {
        try{
            Pageable paging = PageRequest.of(page, size);
            Page<Product> pagePro = productRepository.findProductByName(name, paging);

            Map<String, Object> response = new HashMap<>();
            response.put("products", pagePro.getContent());
            response.put("curPage", pagePro.getNumber());
            response.put("totalPros", pagePro.getTotalElements());
            return response;
        }catch (Exception e){
            throw new Exception("Something went wrong");
        }
    }
}
