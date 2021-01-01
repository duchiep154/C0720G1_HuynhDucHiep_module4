package com.codegym.service.login;

import com.codegym.entity.login.AppRole;
import com.codegym.entity.login.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {
    List<AppRole> findAll();

    AppRole  findById(long id);

    void save(AppRole appRole);

    void update(AppRole appRole);

    void remove(long id);
}
