package com.codegym.service;

import com.codegym.controllers.dto.UserRegistrationDto;
import com.codegym.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
