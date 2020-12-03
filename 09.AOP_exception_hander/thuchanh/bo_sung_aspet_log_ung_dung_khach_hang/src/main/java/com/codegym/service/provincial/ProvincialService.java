package com.codegym.service.provincial;

import com.codegym.entity.Provincial;

import java.util.List;
import java.util.Optional;

public interface ProvincialService {
    List<Provincial> findAll();

    Optional<Provincial> findById(int id);

    void save(Provincial provincial);

    void update(Provincial provincial);

    void remove(int id);
}
