package com.example.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

public class JwtTokenProvider {

    private final String secret;
    private final long validityInMs = 60 * 60 * 1000; // 1 hour

    public JwtTokenProvider() {
        this.secret = "VerySecretKeyForJwtDemo1234567890";
    }

    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }

    // -------- CREATE TOKEN --------
    public String generateToken(Authentication authentication,
                                Long userId,
                                String role,
                                String email) {

        long now = System.currentTimeMillis();
        long expiry = now + validityInMs;

        String content =
                userId + ":" + email + ":" + role + ":" + expiry + ":" + secret;

        return Base64.getEncoder()
                .encodeToString(content.getBytes(StandardCharsets.UTF_8));
    }

    // -------- VALIDATE TOKEN --------
    public boolean validateToken(String token) {
        try {
            String decoded =
                    new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);

            String[] parts = decoded.split(":");

            long expiry = Long.parseLong(parts[3]);
            String tokenSecret = parts[4];

            return tokenSecret.equals(secret) &&
                   expiry > System.currentTimeMillis();

        } catch (Exception e) {
            return false;
        }
    }

    // -------- AUTH OBJECT --------
    public Authentication getAuthentication(String token) {

        String decoded =
                new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);

        String[] parts = decoded.split(":");

        String email = parts[1];
        String role = parts[2];

        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}
