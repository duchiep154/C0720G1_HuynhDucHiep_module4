package com.codegym.service.impl;

import com.codegym.controller.UserRegistrationValidate;
import com.codegym.entity.AppRole;
import com.codegym.entity.AppUser;
import com.codegym.entity.UserRole;
import com.codegym.repository.AppRoleRepository;
import com.codegym.repository.AppUserRepository;
import com.codegym.repository.UserRoleRepository;

import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findByUserName(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<UserRole> userRoles = this.userRoleRepository.findByAppUser(appUser);

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getAppRole().getRoleName());
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getPassword(), grantList);

        return userDetails;
    }

    public List<AppRole> allAppRole(){
        return this.appRoleRepository.findAll();
    }

    public List<AppUser> allAppUser(){
        return this.appUserRepository.findAll();
    }

    public void saveNewUser(AppUser appUser){
        this.appUserRepository.save(appUser);
    }

    public void saveUserRole(AppUser appUser, Long id){
        this.userRoleRepository.save(new UserRole(appUser, this.appRoleRepository.findById(id).orElse(null)));
    }


    @Override
    public AppUser findByUserName(String userName) {
        return appUserRepository.findByUserName(userName);
    }


}

