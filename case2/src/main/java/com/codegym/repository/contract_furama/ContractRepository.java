package com.codegym.repository.contract_furama;

import com.codegym.entity.contract.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
