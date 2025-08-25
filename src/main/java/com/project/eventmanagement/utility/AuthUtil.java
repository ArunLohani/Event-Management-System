package com.project.eventmanagement.utility;

import com.project.eventmanagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {
    @Value("${jwt-secret-key}")
    private String jwtSecretKey;


    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));

    }

    public String getAccessToken(User user){

        return Jwts
                .builder()
                .subject(user.getEmail())
                .claim("userId",user.getUserId())
                .issuedAt(new Date())

                .signWith(getSecretKey())
                .compact();



    }

    public String getEmailFromToken(String token) {

        Claims claims =  Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();


        return claims.getSubject();

    }
}
