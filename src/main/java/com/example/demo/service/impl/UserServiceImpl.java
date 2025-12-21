package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    public UserServiceImpl( UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User registerUser(@RequestBody User user) {
        return repository.save(user);
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
