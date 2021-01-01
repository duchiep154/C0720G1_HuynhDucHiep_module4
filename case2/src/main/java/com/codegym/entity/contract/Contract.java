package com.codegym.entity.contract;

import com.codegym.entity.contract_detail.ContractDetail;
import com.codegym.entity.customer.Customer;
import com.codegym.entity.employee.Employee;
import com.codegym.entity.serviceFurama.ServiceFurama;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
public class Contract implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String startDate;
    private String endDate;
    private Double deposit;
    private Double totalMoney;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private ServiceFurama serviceFurama;

    @OneToOne(mappedBy = "contract")
    private ContractDetail contractDetail;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ServiceFurama getServiceFurama() {
        return serviceFurama;
    }

    public void setServiceFurama(ServiceFurama serviceFurama) {
        this.serviceFurama = serviceFurama;
    }

    public ContractDetail getContractDetail() {
        return contractDetail;
    }

    public void setContractDetail(ContractDetail contractDetail) {
        this.contractDetail = contractDetail;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contract contract = (Contract) target;
        if (contract.startDate.equals("") || contract.endDate.equals("")) {
            errors.rejectValue("startDate", "startDate.empty");
            errors.rejectValue("endDate", "endDate.empty");
        } else if (contract.startDate.compareTo(contract.endDate) >= 0) {
            errors.rejectValue("startDate", "startDate.format");
        }

        if (contract.deposit == null) {
            errors.rejectValue("deposit", "deposit.empty");
        } else if (contract.deposit < 0) {
            errors.rejectValue("deposit", "deposit.format");
        }

        if (contract.totalMoney == null) {
            errors.rejectValue("totalMoney", "totalMoney.empty");
        } else if (contract.totalMoney < 0) {
            errors.rejectValue("totalMoney", "totalMoney.format");
        }
    }
}
