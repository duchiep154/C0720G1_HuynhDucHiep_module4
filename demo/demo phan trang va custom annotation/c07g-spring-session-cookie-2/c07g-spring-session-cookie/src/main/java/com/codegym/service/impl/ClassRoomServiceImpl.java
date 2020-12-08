package com.codegym.service.impl;

import com.codegym.entity.ClassRoom;
import com.codegym.repository.ClassRoomRepository;
import com.codegym.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Override
    public List<ClassRoom> findAll() {
        return this.classRoomRepository.findAll();
    }
}
