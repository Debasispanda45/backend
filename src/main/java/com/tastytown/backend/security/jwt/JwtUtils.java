package com.tastytown.backend.security.jwt;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private static final String JWT_SECRET = "93b194fe0b2b436efa69e7ba600bc4f83a73df4ec98c008a5afcca7389edc898";

    private SecretKey getkey() {
        byte[] keyBytes = Base64.getDecoder().decode(JWT_SECRET);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userId, String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("role", role)
                .signWith(getkey())
                .compact();
    }

    public String getUserId(String token) { // verifyToken, verify, claimUsername
        return Jwts.parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    public String getUserRole(String token) { // verifyToken, verify, claimUsername
        return Jwts.parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role")
                .toString();

    }
}
