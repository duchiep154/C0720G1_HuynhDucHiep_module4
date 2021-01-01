package com.codegym.service.login.impl;

import com.codegym.entity.login.AppRole;
import com.codegym.entity.login.AppUser;
import com.codegym.entity.login.UserRole;
import com.codegym.repository.login.UserRoleRepository;
import com.codegym.service.login.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public Page<UserRole> findAll(Pageable pageable) {
        return this.userRoleRepository.findAll(pageable);
    }

    @Override
    public UserRole findById(long id) {
        return this.userRoleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(UserRole userRole) {
        this.userRoleRepository.save(userRole);

    }

    @Override
    public void update(UserRole userRole) {
        this.userRoleRepository.save(userRole);

    }

    @Override
    public void remove(long id) {
        this.userRoleRepository.deleteById(id);

    }

    @Override
    public Page<UserRole> findAllByAppRole(Pageable pageable, AppRole appRole) {
        return this.userRoleRepository.findAllByAppRole(pageable, appRole);
    }


}
