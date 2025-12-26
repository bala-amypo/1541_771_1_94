package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User user) {
        User saved = userService.registerUser(
                user.getFullName(), user.getEmail(), user.getPassword()
        );
        return ResponseEntity.ok(new ApiResponse(true, "User registered", saved));
    }

    // ---- LOGIN (TOKEN) ----
    @PostMapping("/token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        User user = userService.getByEmail(request.getEmail());

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(), request.getPassword()
                );

        String token = jwtTokenProvider.generateToken(
                auth, user.getId(), user.getRole(), user.getEmail()
        );

        return ResponseEntity.ok(
                new AuthResponse(token, user.getId(), user.getEmail(), user.getRole())
        );
    }
}
