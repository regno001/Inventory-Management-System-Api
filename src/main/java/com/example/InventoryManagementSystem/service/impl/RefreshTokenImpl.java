package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.entity.RefreshToken;
import com.example.InventoryManagementSystem.repository.RefreshTokenRepository;
import com.example.InventoryManagementSystem.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Service
public class RefreshTokenImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String createRefreshToken(String username){
        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setUsername(username);
        refreshToken.setExpiryDate(Instant.now().plusSeconds(604800));
    refreshTokenRepository.save(refreshToken);
    return  token;

    }

    public RefreshToken verifyToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new RuntimeException("Invalid Refresh Token"));
    if (refreshToken.getExpiryDate().isBefore(Instant.now())){
        throw new RuntimeException("Refresh Token Expired");
    }
    return refreshToken;
    }



}
