package com.example.mock2project.controller;

import com.example.mock2project.Entity.Product;
import com.example.mock2project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/cart/{id}")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("addProduct")
    @PreAuthorize("hasAuthority('ROLE_ACTIVE_USER')")
    public ResponseEntity<String> addProductToCart(@RequestParam("product_id") Long product_id,
                                                   @RequestParam("quantityCart") Integer quantityCart,
                                                   @PathVariable("id") Long id)
    {
        cartService.addProductToCart(product_id, quantityCart, id);
        return new ResponseEntity<String>("add product successfull", HttpStatus.OK);
    }

}
