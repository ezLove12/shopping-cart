package com.example.mock2project.service;

import com.example.mock2project.Entity.Cart;
import com.example.mock2project.Entity.Product;
import com.example.mock2project.Entity.User;
import com.example.mock2project.exception.ProductNotExistException;
import com.example.mock2project.exception.UserNotExistException;
import com.example.mock2project.repository.CartRepository;
import com.example.mock2project.repository.ProductRepository;
import com.example.mock2project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public void addProductToCart(Long product_id, Integer quantityEntry, Long id) {

        User existUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotExistException("User not found"));
        Product existProduct = productRepository.findById(product_id).orElseThrow(
                () -> new ProductNotExistException("Product not exist."));

        Cart cart = new Cart();
        cart.setProduct(existProduct);
        cart.setCreatedDate(new Date());
        cart.setUser(existUser);

        // check quantity product database
        int quantity = productRepository.getQuantityProduct(product_id);

        if (quantityEntry < quantity) {
            cart.setQuantity(quantityEntry);

            // update quantity remain
            quantity = quantity - quantityEntry;
            productRepository.remainQuantityProduct(quantity, existProduct.getId());
        }
        else {
            throw new IllegalArgumentException("Not enough product");
        }
        cartRepository.save(cart);
    }
}
