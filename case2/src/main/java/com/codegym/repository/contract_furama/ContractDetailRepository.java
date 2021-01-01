package com.codegym.repository.contract_furama;

import com.codegym.entity.contract_detail.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractDetailRepository extends JpaRepository<ContractDetail, Integer> {
}
