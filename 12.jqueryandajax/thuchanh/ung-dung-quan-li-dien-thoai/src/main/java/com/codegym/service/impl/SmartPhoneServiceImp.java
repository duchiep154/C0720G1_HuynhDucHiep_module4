package com.codegym.service.impl;

import com.codegym.entity.SmartPhone;
import com.codegym.repository.SmartPhoneRepository;
import com.codegym.service.SmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartPhoneServiceImp implements SmartPhoneService {
    @Autowired
    private SmartPhoneRepository smartPhoneRepository;


    @Override
    public Iterable<SmartPhone> findAll() {
        return this.smartPhoneRepository.findAll();
    }

    @Override
    public SmartPhone findById(Integer id) {
        return this.smartPhoneRepository.findById(id).orElse(null);
    }

    @Override
    public SmartPhone save(SmartPhone phone) {
        return this.smartPhoneRepository.save(phone);
    }

    @Override
    public SmartPhone remove(Integer id) {
        SmartPhone smartphone = findById(id);
        smartPhoneRepository.deleteById(id);
        return smartphone;
    }
}
