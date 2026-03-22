package com.example.InventoryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String type;
    private String username;
    private String role;
    private long accessTokenExpiresIn;
    private long refreshTokenExpiresIn;
}
