package com.myph.blogmanagement.utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtilsHelper {

    private final String privateKey;

    public JwtUtilsHelper() {
        Dotenv dotenv = Dotenv.load();  // Load các biến môi trường từ file .env
        this.privateKey = dotenv.get("JWT_PRIVATE_KEY");  // Lấy giá trị của JWT_PRIVATE_KEY
    }

    public String generateToken(String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        return Jwts.builder().subject(data).signWith(key).compact();
    }

    public boolean verifyToken (String token) {
        try{
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
