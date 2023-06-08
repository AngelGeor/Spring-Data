package com.example.springbootdemo.services;


import com.example.springbootdemo.models.User;

public interface UserService {
    void register(String username, int age);

    User findByUsername(String username);
}
