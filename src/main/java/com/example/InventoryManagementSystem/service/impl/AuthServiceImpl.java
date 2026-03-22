package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.AuthResponseDTO;
import com.example.InventoryManagementSystem.dto.LoginRequestDto;
import com.example.InventoryManagementSystem.dto.RegisterRequestDto;
import com.example.InventoryManagementSystem.entity.Role;
import com.example.InventoryManagementSystem.entity.User;
import com.example.InventoryManagementSystem.repository.UserRepository;
import com.example.InventoryManagementSystem.security.JwtService;
import com.example.InventoryManagementSystem.service.AuthService;
import com.example.InventoryManagementSystem.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Relation;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public String register(RegisterRequestDto request) {

        User user= new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        return "User Registered Successfully ";
    }

    @Override
    public AuthResponseDTO login(LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String accessToken = jwtService.generateToken(request.getEmail());
        String refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
         User user= userRepository.findByEmail(request.getEmail())
                 .orElseThrow(()-> new RuntimeException("User Not Found"));
        return new AuthResponseDTO(
                accessToken,
                refreshToken,
                "Bearer",
                request.getEmail(),
                user.getRole().name(),
                3600,
                604800
        );
    }
}