package com.codegym.service.service.impl;

import com.codegym.entity.service.RentType;
import com.codegym.entity.service.ServiceType;
import com.codegym.repository.service.RentTypeRepository;
import com.codegym.repository.service.ServiceRepository;
import com.codegym.repository.service.ServiceTypeRepository;
import com.codegym.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository xRepository;

    @Autowired
    private RentTypeRepository rentTypeRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Override
    public List<com.codegym.entity.service.Service> findAll() {
        return this.xRepository.findAll();
    }

    @Override
    public Page<com.codegym.entity.service.Service> findAll(Pageable pageable) {
        return this.xRepository.findAll(pageable);
    }

    @Override
    public com.codegym.entity.service.Service findById(String id) {
        return this.xRepository.findById(id).orElse(null);
    }

    @Override
    public void save(com.codegym.entity.service.Service service) {
        this.xRepository.save(service);
    }

    @Override
    public void update(com.codegym.entity.service.Service service) {
        this.xRepository.save(service);
    }

    @Override
    public void remove(String id) {
        this.xRepository.deleteById(id);
    }

    @Override
    public List<RentType> findAllRentType() {
        return this.rentTypeRepository.findAll();
    }

    @Override
    public List<ServiceType> findAllServiceType() {
        return this.serviceTypeRepository.findAll();
    }

    @Override
    public Page<com.codegym.entity.service.Service> findByNameContaining(Pageable pageable, String name) {
        return this.xRepository.findByNameContaining(pageable, name);
    }
}
