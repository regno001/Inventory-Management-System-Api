package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.AuthResponseDTO;
import com.example.InventoryManagementSystem.dto.LoginRequestDto;
import com.example.InventoryManagementSystem.dto.RegisterRequestDto;

public interface AuthService {

    String register(RegisterRequestDto request);
    AuthResponseDTO login(LoginRequestDto request);


}
