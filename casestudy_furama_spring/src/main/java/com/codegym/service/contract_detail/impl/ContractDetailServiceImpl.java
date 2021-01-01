package com.codegym.service.contract_detail.impl;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.contract_detail.AttachService;
import com.codegym.entity.contract_detail.ContractDetail;
import com.codegym.repository.contract.ContractRepository;
import com.codegym.repository.contract_detail.AttachServiceReposity;
import com.codegym.repository.contract_detail.ContractDetailRepository;
import com.codegym.service.contract_detail.ContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractDetailServiceImpl implements ContractDetailService {
    @Autowired
    private ContractDetailRepository xRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private AttachServiceReposity attachServiceReposity;


    @Override
    public List<ContractDetail> findAll() {
        return this.xRepository.findAll();
    }

    @Override
    public Page<ContractDetail> findAll(Pageable pageable) {
        return this.xRepository.findAll(pageable);
    }

    @Override
    public ContractDetail findById(Integer id) {
        return this.xRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ContractDetail contractDetail) {
        this.xRepository.save(contractDetail);
    }

    @Override
    public void update(ContractDetail contractDetail) {
        this.xRepository.save(contractDetail);
    }

    @Override
    public void remove(Integer id) {
        this.xRepository.findById(id);
    }

    @Override
    public List<AttachService> findAllAttachService() {
        return this.attachServiceReposity.findAll();
    }

    @Override
    public List<Contract> findAllContract() {
        return this.contractRepository.findAll();
    }
}
