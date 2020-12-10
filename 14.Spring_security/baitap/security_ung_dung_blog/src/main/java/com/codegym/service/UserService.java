package com.codegym.service;

import com.codegym.controller.UserRegistrationValidate;
import com.codegym.entity.AppRole;
import com.codegym.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<AppRole> allAppRole();
    List<AppUser> allAppUser();
    void saveNewUser(AppUser appUser);
     void saveUserRole(AppUser appUser, Long id);
    AppUser findByUserName(String userName);

//    AppUser save(UserRegistrationValidate registration);
}
