package com.codegym.entity.contract_detail;

import com.codegym.entity.contract.Contract;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ContractDetail implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contract_attach_service",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "attach_service_id"))
    private Set<AttachService> attachServiceSet;

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

    public Set<AttachService> getAttachServiceSet() {
        return attachServiceSet;
    }

    public void setAttachServiceSet(Set<AttachService> attachServiceSet) {
        this.attachServiceSet = attachServiceSet;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractDetail contractDetail = (ContractDetail) target;
        if (contractDetail.quantity == null) {
            errors.rejectValue("quantity", "quantity.empty");
        } else if (contractDetail.quantity < 0) {
            errors.rejectValue("quantity", "quantity.format");
        }
    }
}
