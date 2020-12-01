package com.codegym.service;

import com.codegym.entity.Province;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {
    List<Province> findAll();

    Optional<Province> findById(int id);

    void save(Province province);

    void update(Province province);

    void remove(int id);
}
