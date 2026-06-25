package com.learner.controller;

import com.learner.dto.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {

        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());

        boolean success =
                "admin".equals(request.getUsername()) &&
                        "12345".equals(request.getPassword());

        if (success) {
            return Map.of(
                    "success", true,
                    "message", "Login successful"
            );
        }

        return Map.of(
                "success", false,
                "message", "Invalid credentials"
        );
    }
}
