package com.codegym.service.login;

import com.codegym.entity.login.AppRole;
import com.codegym.entity.login.AppUser;
import com.codegym.entity.login.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    Page<UserRole> findAll(Pageable pageable);

    UserRole findById(long id);

    void save(UserRole userRole);

    void update(UserRole userRole);

    void remove(long id);

    Page<UserRole> findAllByAppRole(Pageable pageable, AppRole appRole);
}

