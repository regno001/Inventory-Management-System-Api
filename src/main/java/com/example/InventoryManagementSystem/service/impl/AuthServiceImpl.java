package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.LoginRequestDto;
import com.example.InventoryManagementSystem.dto.RegisterRequestDto;
import com.example.InventoryManagementSystem.entity.Role;
import com.example.InventoryManagementSystem.entity.User;
import com.example.InventoryManagementSystem.repository.UserRepository;
import com.example.InventoryManagementSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    public final UserRepository userRepository;

    @Override
    public String register(RegisterRequestDto request) {
        User user= new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);

        userRepository.save(user);

        return "User Registered Successfully ";
    }

    @Override
    public String Login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail());

        if(user== null){
            throw new RuntimeException("User not Found");

        }if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return "Login Successful";

    }
}
