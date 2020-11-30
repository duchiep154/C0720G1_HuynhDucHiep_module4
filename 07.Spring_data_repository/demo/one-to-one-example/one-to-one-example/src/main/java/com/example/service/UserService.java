package com.example.service;

import com.example.entity.User;

public interface UserService {
    void save(User user);
    User findById(Long id);
}
