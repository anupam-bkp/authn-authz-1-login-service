package com.learner.controller;

import com.learner.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {

        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());

        if (request.getUsername().equalsIgnoreCase("Anupam")
                && request.getPassword().equalsIgnoreCase("admin123")) {

        /*String redirectUri = String.format("%s?username=%s&password=%s",
             "http://ec2-18-60-45-64.ap-south-2.compute.amazonaws.com/v1/auth/generate/token", request.getUsername(), request.getPassword());
*/
            //Redirect to auth-service for token generation
            String redirectUri = ServletUriComponentsBuilder.fromUriString("http://ec2-18-60-45-64.ap-south-2.compute.amazonaws.com")
                    .path("/v1/auth/generate/token")
                    .queryParam("username", request.getUsername())
                    .queryParam("password", request.getPassword())
                    .toUriString();

//        HttpHeaders headers = new HttpHeaders();
//        String cookie = String.format("username=%s; password=%s", request.getUsername(), request.getPassword());
//        headers.add(HttpHeaders.SET_COOKIE, cookie);

//        headers.add(HttpHeaders.LOCATION, redirectUri);
//        return new ResponseEntity<>(headers, HttpStatus.FOUND);
            return ResponseEntity.ok(Map.of("success", true,
                    "redirectUrl", redirectUri));
        } else {
            return ResponseEntity.ok(Map.of("success", false,
                    "invalidMsg", "Invalid username or password!"));
        }
    }
}
