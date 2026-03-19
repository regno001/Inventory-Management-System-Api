package com.example.InventoryManagementSystem.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException{
        String authHeader = request.getHeader("Authorization");
    if(authHeader==null || !authHeader.startsWith("Bearer ")){
        filterChain.doFilter(request , response);
        return;
    }

    String token = authHeader.substring(7);

    String email = jwtService.extractUsername(token);

    if(email != null){
        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(email ,null ,new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
    filterChain.doFilter(request,response);

    }
}
