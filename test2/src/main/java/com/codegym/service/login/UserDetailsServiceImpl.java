package com.codegym.service.login;

import com.codegym.entity.login.AppUser;
import com.codegym.entity.login.Role;
import com.codegym.entity.login.UserRole;
import com.codegym.repository.login.RoleRepository;
import com.codegym.repository.login.UserRepository;
import com.codegym.repository.login.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.userRepository.findByUserName(userName);

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
                appUser.getEncrytedPassword(), grantList);

        return userDetails;
    }

    public List<Role> allAppRole() {
        return this.roleRepository.findAll();
    }

    public void saveNewUser(AppUser appUser) {
        this.userRepository.save(appUser);
    }

    public void saveUserRole(AppUser appUser, Long id) {
        this.userRoleRepository.save(new UserRole(appUser, this.roleRepository.findById(id).orElse(null)));
    }

    public String saveUser(AppUser appUser, Long id) {
        appUser.setEncrytedPassword(this.bCryptPasswordEncoder.encode(appUser.getEncrytedPassword()));
        appUser.setUserId((long) (Math.random() * 1000));
        appUser.setEnabled(true);
        saveNewUser(appUser);
        saveUserRole(appUser, id);
        return "Register Complete !";
    }
}
