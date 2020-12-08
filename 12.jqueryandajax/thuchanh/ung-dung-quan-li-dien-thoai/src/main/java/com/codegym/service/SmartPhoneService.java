package com.codegym.service;

import com.codegym.entity.SmartPhone;

public interface SmartPhoneService {
    Iterable<SmartPhone> findAll();

    SmartPhone findById(Integer id);

    SmartPhone save(SmartPhone phone);

    SmartPhone remove(Integer id);
}
