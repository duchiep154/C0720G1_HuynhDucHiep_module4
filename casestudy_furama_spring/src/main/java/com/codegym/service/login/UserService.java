package com.codegym.service.login;


import com.codegym.entity.login.AppRole;
import com.codegym.entity.login.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<AppRole> allAppRole();
    List<AppUser> allAppUser();
    void saveNewUser(AppUser appUser);
     void saveUserRole(AppUser appUser,AppRole appRole);
    AppUser findByUserName(String userName);

    void update(AppUser appUser);

//    AppUser save(UserRegistrationValidate registration);
}
