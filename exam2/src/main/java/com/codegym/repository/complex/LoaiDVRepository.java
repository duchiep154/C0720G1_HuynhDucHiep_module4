package com.codegym.repository.complex;

import com.codegym.entity.complex.LoaiDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiDVRepository extends JpaRepository<LoaiDV,Integer> {
}
