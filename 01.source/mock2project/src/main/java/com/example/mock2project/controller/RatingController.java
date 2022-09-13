package com.example.mock2project.controller;


import com.example.mock2project.Entity.Rating;
import com.example.mock2project.dto.RatingDTO;
import com.example.mock2project.service.RatingService;
import com.example.mock2project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class RatingController {
    @Autowired
    RatingService ratingService;
    @Autowired
    UserService userService;
    @PostMapping("/rating")
    public ResponseEntity<Map<String, String>> rating(@RequestParam  Long pid,
                                                      HttpServletRequest req,
                                                      @RequestBody RatingDTO ratingDTO) throws Exception {
        Long user_id = userService.getUser((String)req.getAttribute("username")).getId();
        Rating r = ratingService.getRating(user_id, pid);
        Map<String, String> response = new HashMap<>();
        if(r!=null) {
            if (r.getVote() == null) {
                int vote = ratingDTO.getVote();
                log.info(String.valueOf(ratingDTO.getVote()));
                r.setVote(vote);
                ratingService.saveRating(r);

                response.put("message", "Rating for product " + pid + " success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "You already rating product "+pid);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            response.put("message", "You don't have permission to rate product "+pid);
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }
}
