package com.example.mock2project.controller;

import com.example.mock2project.Entity.Product;
import com.example.mock2project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<Map<String, Object>> findProduct(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int size) throws Exception {
        return new ResponseEntity<>(productService.getAllProduct(page, size), HttpStatus.OK);
    }
}
