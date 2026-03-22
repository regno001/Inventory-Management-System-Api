package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.entity.RefreshToken;
import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {

    String createRefreshToken(String username);

    RefreshToken verifyToken(String token);
}
