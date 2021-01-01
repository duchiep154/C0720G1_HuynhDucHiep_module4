package com.codegym.entity.contract;

import com.codegym.entity.contract_detail.ContractDetail;
import com.codegym.entity.customer.Customer;
import com.codegym.entity.employee.Employee;
import com.codegym.entity.service.Service;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "contract")
public class Contract implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String startDate;

    private String endDate;

    private Double deposit;

    private Double totalMoney;

    // ------------------------------ Tạo mối quan hệ ------------------------------
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<ContractDetail> contractDetailList;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    // -------------------------------------------------------------------------------------

    public Contract() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<ContractDetail> getContractDetailList() {
        return contractDetailList;
    }

    public void setContractDetailList(List<ContractDetail> contractDetailList) {
        this.contractDetailList = contractDetailList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contract contract = (Contract) target;

        // -------------------------- Date ----------------------------------------
        if (contract.startDate.equals("") || contract.endDate.equals("")) {
            errors.rejectValue("startDate", "startDate.empty");
            errors.rejectValue("endDate", "endDate.empty");
        } else if (contract.startDate.compareTo(contract.endDate) >= 0) {
            errors.rejectValue("startDate", "startDate.format");
            errors.rejectValue("endDate", "endDate.format");
        }

        // ------------------------------- Deposit ---------------------------------
        if (contract.deposit == null) {
            errors.rejectValue("deposit", "deposit.empty");
        } else if (contract.deposit <= 0) {
            errors.rejectValue("deposit", "deposit.positive");
        }

        // ------------------------------ Total Money --------------------------------
        if (contract.totalMoney == null) {
            errors.rejectValue("totalMoney", "totalMoney.empty");
        } else if (contract.totalMoney <= 0) {
            errors.rejectValue("totalMoney", "totalMoney.positive");
        }
    }
}
