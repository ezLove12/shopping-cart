package com.example.mock2project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class handleAccessDeniedEx {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex, WebRequest request){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
