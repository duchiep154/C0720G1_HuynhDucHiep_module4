package com.codegym.service.provincial;

import com.codegym.entity.Provincial;
import com.codegym.repository.ProvincialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvincialServiceImp implements ProvincialService {
    @Autowired
    private ProvincialRepository provincialRepository;

    @Override
    public List<Provincial> findAll() {
        return this.provincialRepository.findAll();
    }

    @Override
    public Optional<Provincial> findById(int id) {
        return this.provincialRepository.findById(id);
    }

    @Override
    public void save(Provincial provincial) {
        this.provincialRepository.save(provincial);
    }

    @Override
    public void update(Provincial provincial) {
        this.provincialRepository.save(provincial);
    }

    @Override
    public void remove(int id) {
        this.provincialRepository.deleteById(id);
    }
}
