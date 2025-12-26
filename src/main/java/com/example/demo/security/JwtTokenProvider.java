// package com.example.demo.security;

// import org.springframework.security.core.Authentication;

// import java.nio.charset.StandardCharsets;
// import java.util.Base64;

// public class JwtTokenProvider {

//     private final String secret;
//     private final long validityInMs = 60 * 60 * 1000; // 1 hour

//     public JwtTokenProvider(String secret) {
//         this.secret = secret;
//     }

//     public String generateToken(Authentication authentication,
//                                 Long userId,
//                                 String role,
//                                 String email) {

//         long now = System.currentTimeMillis();
//         long expiry = now + validityInMs;

//         String content = userId + ":" + email + ":" + role + ":" + expiry + ":" + secret;

//         return Base64.getEncoder()
//                 .encodeToString(content.getBytes(StandardCharsets.UTF_8));
//     }
// }
