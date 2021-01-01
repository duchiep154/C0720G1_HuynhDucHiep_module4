package com.cdoegym.aop_session.service.impl;

import com.cdoegym.aop_session.entity.CardStudent;
import com.cdoegym.aop_session.repository.CardStudentRepository;
import com.cdoegym.aop_session.service.CardStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardStudentServiceImpl implements CardStudentService {

    @Autowired
    private CardStudentRepository cardStudentRepository;
    @Override
    public List<CardStudent> getAll() {
        return cardStudentRepository.findAll();
    }
}
