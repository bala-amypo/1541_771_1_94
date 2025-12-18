package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User registerUser(String fullName, String email, String password) {
        User user = new User(fullName, email, password, "USER");
        return repository.save(user);
    }

    public User getByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }
}
