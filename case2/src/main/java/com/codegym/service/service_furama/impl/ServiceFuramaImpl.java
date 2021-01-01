package com.codegym.service.service_furama.impl;

import com.codegym.entity.serviceFurama.RentType;
import com.codegym.entity.serviceFurama.ServiceFurama;
import com.codegym.entity.serviceFurama.ServiceType;
import com.codegym.repository.service_furama.RentTypeRepository;
import com.codegym.repository.service_furama.ServiceRepository;
import com.codegym.repository.service_furama.ServiceTypeRepository;
import com.codegym.service.service_furama.ServiceFuramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFuramaImpl implements ServiceFuramaService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private RentTypeRepository rentTypeRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Override
    public List<ServiceFurama> allService() {
        return this.serviceRepository.findAll();
    }

    @Override
    public List<RentType> allRentType() {
        return this.rentTypeRepository.findAll();
    }

    @Override
    public List<ServiceType> allServiceType() {
        return this.serviceTypeRepository.findAll();
    }

    @Override
    public void save(ServiceFurama serviceFurama) {
        this.serviceRepository.save(serviceFurama);
    }
}
