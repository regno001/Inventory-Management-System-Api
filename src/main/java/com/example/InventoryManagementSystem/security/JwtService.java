package com.example.InventoryManagementSystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY="uDJ7ALPk07uoembNyVcCvmbsfPy61kknIASNDmrHUFe";

    private SecretKey getSignInKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email){
        return Jwts.builder().subject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 1000*60*60)).signWith(getSignInKey()).compact();
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();

    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

}
