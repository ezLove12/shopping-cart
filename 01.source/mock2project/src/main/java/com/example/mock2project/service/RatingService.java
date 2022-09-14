package com.example.mock2project.service;

import com.example.mock2project.Entity.OrderDetails;
import com.example.mock2project.Entity.Product;
import com.example.mock2project.Entity.Rating;
import com.example.mock2project.Entity.User;
import com.example.mock2project.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PostPersist;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepo;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    public Rating saveRating(Rating rating){
        return ratingRepo.save(rating);
    }

    @PostPersist
    public void addUserIdAndProId(OrderDetails orderDetails){
        Rating r = new Rating();
        User u = orderDetails.getOrder().getUser();
        Product p = orderDetails.getProduct();
        r.setUser(u);
        r.setProduct(p);
        ratingRepo.save(r);
    }

    public Rating getRating(Long user_id, Long product_id){
        return ratingRepo.findByUser_idAndProduct_id(user_id, product_id);
    }
}
