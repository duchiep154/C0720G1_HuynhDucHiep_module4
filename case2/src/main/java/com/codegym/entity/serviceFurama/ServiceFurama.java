package com.codegym.entity.serviceFurama;

import com.codegym.entity.contract.Contract;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class ServiceFurama implements Validator {
    @Id
    @NotBlank(message = "Please enter ID of service !")
    @Pattern(regexp = "^(DV-)\\d{4}$", message = "Invalid ID ! Format ID is DV-XXXX with X is number from 0 to 9 !")
    private String id;

    @NotBlank(message = "Please input name of service !")
    private String name;

    private Double area;
    private Double cost;
    private Integer maxPeople;

    @NotBlank(message = "Please enter standard room of service !")
    private String standardRoom;

    @NotBlank(message = "Please enter description other convenience of service !")
    private String descriptionOtherConvenience;

    private Double poolArea;
    private Integer numberOfFloors;

    @ManyToOne
    @JoinColumn(name = "rent_type_id", referencedColumnName = "id")
    private RentType rentType;

    @ManyToOne
    @JoinColumn(name = "service_type_id", referencedColumnName = "id")
    private ServiceType serviceType;

    @OneToMany(mappedBy = "serviceFurama", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Contract> contractList;

    public ServiceFurama() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescriptionOtherConvenience() {
        return descriptionOtherConvenience;
    }

    public void setDescriptionOtherConvenience(String descriptionOtherConvenience) {
        this.descriptionOtherConvenience = descriptionOtherConvenience;
    }

    public Double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(Double poolArea) {
        this.poolArea = poolArea;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(Integer numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public RentType getRentType() {
        return rentType;
    }

    public void setRentType(RentType rentType) {
        this.rentType = rentType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServiceFurama serviceFurama = (ServiceFurama) target;
        if (serviceFurama.area == null) {
            errors.rejectValue("area", "area.empty");
        } else if (serviceFurama.area <= 0) {
            errors.rejectValue("area", "area.format");
        }

        if (serviceFurama.cost == null) {
            errors.rejectValue("cost", "cost.empty");
        } else if (serviceFurama.cost <= 0) {
            errors.rejectValue("cost", "cost.format");
        }

        if (serviceFurama.maxPeople == null) {
            errors.rejectValue("maxPeople", "maxPeople.empty");
        } else if (serviceFurama.maxPeople <= 0) {
            errors.rejectValue("maxPeople", "maxPeople.format");
        }

        if (serviceFurama.poolArea == null) {
            errors.rejectValue("poolArea", "poolArea.empty");
        } else if (serviceFurama.poolArea < 0) {
            errors.rejectValue("poolArea", "poolArea.format");
        }

        if (serviceFurama.numberOfFloors == null) {
            errors.rejectValue("numberOfFloors", "numberOfFloors.empty");
        } else if (serviceFurama.numberOfFloors <= 0) {
            errors.rejectValue("numberOfFloors", "numberOfFloors.format");
        }
    }
}