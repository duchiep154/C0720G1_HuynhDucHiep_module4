package com.codegym.service.contract_detail;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.contract_detail.AttachService;
import com.codegym.entity.contract_detail.ContractDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractDetailService {
    List<ContractDetail> findAll();

    Page<ContractDetail> findAll(Pageable pageable);

    ContractDetail findById(Integer id);

    void save(ContractDetail contractDetail);

    void update(ContractDetail contractDetail);

    void remove(Integer id);

    List<AttachService> findAllAttachService();

    List<Contract> findAllContract();
}
