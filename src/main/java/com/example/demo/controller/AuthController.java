package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authManager;

    public AuthController(UserService userService,
                          JwtTokenProvider tokenProvider,
                          AuthenticationManager authManager) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authManager = authManager;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user) {
        User saved = userService.registerUser(
                user.getFullName(),
                user.getEmail(),
                user.getPassword()
        );

        return new ApiResponse(true, "Registered successfully", saved.getId());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        UserDetails principal = (UserDetails) auth.getPrincipal();
        User domainUser = userService.getByEmail(principal.getUsername());

        String token = tokenProvider.generateToken(
                domainUser.getId(),
                domainUser.getEmail(),
                domainUser.getRole()
        );

        return new AuthResponse(
                token,
                domainUser.getId(),
                domainUser.getEmail(),
                domainUser.getRole()
        );
    }
}
