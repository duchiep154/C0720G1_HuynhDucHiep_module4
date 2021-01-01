package com.codegym.service.login.impl;

import com.codegym.entity.login.AppRole;
import com.codegym.repository.login.AppRoleRepository;
import com.codegym.service.login.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private AppRoleRepository appRoleRepository;


    @Override
    public List<AppRole> findAll() {
        return this.appRoleRepository.findAll();
    }

    @Override
    public AppRole findById(long id) {
        return this.appRoleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AppRole appRole) {
        this.appRoleRepository.save(appRole);

    }

    @Override
    public void update(AppRole appRole) {
        this.appRoleRepository.save(appRole);

    }

    @Override
    public void remove(long id) {
        this.appRoleRepository.deleteById(id);

    }
}
