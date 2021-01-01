package com.codegym.service.contract;

import com.codegym.entity.contract.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> allContract();

    void save(Contract contract);
}
