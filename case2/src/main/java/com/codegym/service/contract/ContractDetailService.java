package com.codegym.service.contract;

import com.codegym.entity.contract_detail.AttachService;
import com.codegym.entity.contract_detail.ContractDetail;

import java.util.List;

public interface ContractDetailService {
    void save(ContractDetail contractDetail);

    List<AttachService> allAttachService();
}
