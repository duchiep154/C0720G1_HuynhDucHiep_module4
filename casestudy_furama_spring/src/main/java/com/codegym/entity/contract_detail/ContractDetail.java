package com.codegym.entity.contract_detail;


import com.codegym.entity.contract.Contract;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;


@Entity(name = "contract_detail")
public class ContractDetail implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;


    // ----------------------------- Tạo mối quan hệ ----------------------------
    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "attach_service_id", referencedColumnName = "id")
    private AttachService attachService;
    // ---------------------------------------------------------------------------

    public ContractDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public AttachService getAttachService() {
        return attachService;
    }

    public void setAttachService(AttachService attachService) {
        this.attachService = attachService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractDetail contractDetail = (ContractDetail) target;
        if(contractDetail.quantity == null){
            errors.rejectValue("quantity", "quantity.empty");
        } else if (contractDetail.quantity < 0){
            errors.rejectValue("quantity", "quantity.positive");
        }
    }
}
