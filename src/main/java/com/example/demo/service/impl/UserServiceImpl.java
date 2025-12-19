package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository repository;
    public UserServiceImpl( UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User registerUser(User user) {
        return user;
    }

    // @Override
    // public String getByEmail() {
    //     return "fetch successfully";
    // }

    // @Override
    // public boolean exists(String email) {
    //     return false;
    // }
}
