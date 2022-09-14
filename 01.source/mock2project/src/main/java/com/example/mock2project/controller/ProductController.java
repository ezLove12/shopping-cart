package com.example.mock2project.controller;

import com.example.mock2project.service.CateService;
import com.example.mock2project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CateService cateService;

    @GetMapping("/product")
    public ResponseEntity<Map<String, Object>> findProduct(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "3") int size)
            throws Exception {
        return new ResponseEntity<>(productService.getAllProduct(page, size), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> findProductbyName(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "3") int size,
                                                                 @RequestParam String name ) throws Exception {
        return new ResponseEntity<>(productService.findProductByName(page, size, name), HttpStatus.OK);
    }

    @GetMapping("/{cate}")
    public ResponseEntity<Map<String, Object>> findProductByCateAndSort(@PathVariable String cate,
                                                                        @RequestParam(defaultValue = "name") String order,
                                                                        @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "3") int size) throws Exception {
        Long cate_id = cateService.getCateIdByName(cate);
        return new ResponseEntity<>(productService.findProductByCateIdAndSorting(cate_id, order, page, size), HttpStatus.OK);
    }
}
