package com.example.mock2project.controller;

import com.example.mock2project.Entity.Order;
import com.example.mock2project.Entity.UserDetail;
import com.example.mock2project.dto.UserDetailDTO;
import com.example.mock2project.dto.UserInfo;
import com.example.mock2project.service.JWTService;
import com.example.mock2project.service.OrderService;
import com.example.mock2project.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user/profile/{id}")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;

    @Autowired
    OrderService orderService;
    @PostMapping("/changepassword")
    @PreAuthorize("hasAuthority('ROLE_ACTIVE_USER')")
    public ResponseEntity changePassword(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) throws Exception {
        String username = (String) request.getAttribute("username");
        Long userTokenId = userService.getUser(username).getId();
        if(userTokenId == id){
            String new_password = request.getParameter("new_password");
            String ole_password = request.getParameter("old_password");
            userService.changePassword(new_password,id, ole_password);
            return ResponseEntity.ok("Change password success");
        }else{
            throw new AccessDeniedException("You don't have permission");
        }
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ROLE_ACTIVE_USER')")
    public ResponseEntity<Map<String, Object>> getUserOrder(@PathVariable Long id,
                                                            HttpServletRequest request, HttpServletResponse response,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) throws Exception {
        Long userTokenId = userService.getUser((String)request.getAttribute("username")).getId();
        log.info(userTokenId.toString());
        if(userTokenId == id){
            return new ResponseEntity<>(orderService.getOrdersByUserId(id, page, size), HttpStatus.OK);
        }else{
            throw new AccessDeniedException("You don't have permission");
        }
    }

    @GetMapping("/total")
    @PreAuthorize("hasAuthority('ROLE_ACTIVE_USER')")
    public ResponseEntity<String> getPurchasepriceByUserId(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
        Long userTokenId = userService.getUser((String)request.getAttribute("username")).getId();
        if(userTokenId == id){
            return new ResponseEntity<>(orderService.getPurchasePriceByUserId(id), HttpStatus.OK);
        }else{
            throw new AccessDeniedException("You don't have permission");
        }
    }

    @PutMapping("/editInfo")
    @PreAuthorize("hasAuthority('ROLE_ACTIVE_USER')")
    public ResponseEntity<UserDetail> editInfoUser(@PathVariable("id")  Long id,
                                                   @RequestBody UserDetail userDetail) {

        return new ResponseEntity<UserDetail>(userService.editInfoUser(userDetail, id), HttpStatus.OK);
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('ROLE_ACTIVE_USER')")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable("id") Long user_id) {
        return new ResponseEntity<UserInfo>(userService.viewUserInfo(user_id), HttpStatus.OK);
    }
}

