package com.example.mock2project.controller;

import com.example.mock2project.Entity.Product;
import com.example.mock2project.dto.ProductDTO;
import com.example.mock2project.service.CateService;
import com.example.mock2project.service.FileService;
import com.example.mock2project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CateService cateService;
    @Autowired
    FileService fileService;
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

    @PostMapping("/product")
    @PreAuthorize("hasAuthority('ROLE_SALE_ADMIN')")
    public ResponseEntity<String> saveProduct(@RequestParam("file")MultipartFile multipartFile,
                                               @RequestParam("name") String name,
                                               @RequestParam("quantity") Integer quantity,
                                               @RequestParam("des") String des,
                                               @RequestParam("cate_name") String cate_name,
                                               @RequestParam("price") String price) throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPname(name);
        productDTO.setQuantity(quantity);
        productDTO.setPprice(price);
        productDTO.setDes(des);
        productDTO.setCate_name(cate_name);
        productDTO.setStatus(1);
        String file_url = fileService.upload(multipartFile);
        productDTO.setImage_link(file_url);
        Product pro = productService.saveProduct(productDTO);
        return new ResponseEntity<>("Save Product "+ name +" success", HttpStatus.CREATED);
    }
}
