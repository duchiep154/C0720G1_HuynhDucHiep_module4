package com.codegym.repository;

import com.codegym.entity.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartPhoneRepository extends JpaRepository<SmartPhone, Integer > {
}
