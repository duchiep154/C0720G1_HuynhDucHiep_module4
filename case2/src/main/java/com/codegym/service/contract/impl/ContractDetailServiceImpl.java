package com.codegym.service.contract.impl;

import com.codegym.entity.contract_detail.AttachService;
import com.codegym.entity.contract_detail.ContractDetail;
import com.codegym.repository.contract_furama.AttachServiceRepository;
import com.codegym.repository.contract_furama.ContractDetailRepository;
import com.codegym.service.contract.ContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractDetailServiceImpl implements ContractDetailService {
    @Autowired
    private ContractDetailRepository contractDetailRepository;

    @Autowired
    private AttachServiceRepository attachServiceRepository;

    @Override
    public void save(ContractDetail contractDetail) {
        this.contractDetailRepository.save(contractDetail);
    }

    @Override
    public List<AttachService> allAttachService() {
        return this.attachServiceRepository.findAll();
    }
}
