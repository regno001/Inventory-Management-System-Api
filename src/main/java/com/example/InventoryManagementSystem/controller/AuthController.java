package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.LoginRequestDto;
import com.example.InventoryManagementSystem.dto.RegisterRequestDto;
import com.example.InventoryManagementSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto request){
      String response = authService.register(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request){
        String reponse = authService.Login(request);

        return ResponseEntity.ok(reponse);
    }

}
