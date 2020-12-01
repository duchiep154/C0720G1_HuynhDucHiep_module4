package com.codegym.service.impl;

import com.codegym.entity.Province;
import com.codegym.repository.ProvinceRepository;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImp implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> findAll() {
        return this.provinceRepository.findAll();
    }

    @Override
    public Optional<Province> findById(int id) {
        return this.provinceRepository.findById(id);
    }

    @Override
    public void save(Province provincial) {
        this.provinceRepository.save(provincial);
    }

    @Override
    public void update(Province province) {
        this.provinceRepository.save(province);
    }

    @Override
    public void remove(int id) {
        this.provinceRepository.deleteById(id);
    }
}
