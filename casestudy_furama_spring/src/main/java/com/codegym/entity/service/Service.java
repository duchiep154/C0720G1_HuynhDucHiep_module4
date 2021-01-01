package com.codegym.entity.service;

import com.codegym.entity.contract.Contract;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity(name = "service")
public class Service implements Validator {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.codegym.common.MyGeneratorEmp")
    @Column(name="id")
    private String id;

    @NotBlank(message = "Do Not Blank")
    private String name;

    private Double area;

    private Double cost;

    private Integer maxPeople;

    @NotBlank(message = "Do Not Blank")
    private String standardRoom;

    @NotBlank(message = "Do Not Blank")
    private String descriptionOtherConvenience;

    private Double poolArea;

    private Integer numberOfFloors;


    // ------------------------------ Tạo mối quan hệ ------------------------------------------------
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Contract> contractList;

    @ManyToOne
    @JoinColumn(name = "rent_type_id", referencedColumnName = "id")
    private RentType rentType;

    @ManyToOne
    @JoinColumn(name = "service_type_id", referencedColumnName = "id")
    private ServiceType serviceType;
    // ----------------------------------------------------------------------------------------------------

    public Service() {
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

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Service service = (Service) target;
        // ------------------------ Area ---------------------------------
        if(service.area == null){
            errors.rejectValue("area", "area.empty");
        } else if (service.area <= 0){
            errors.rejectValue("area","area.positive");
        }

        // -------------------------- Cost ---------------------------------
        if(service.cost == null){
            errors.rejectValue("cost", "cost.empty");
        } else if (service.cost <= 0){
            errors.rejectValue("cost","cost.positive");
        }

        // --------------------------- Max People -------------------------------
        if(service.maxPeople == null){
            errors.rejectValue("maxPeople", "maxPeople.empty");
        } else if (service.maxPeople <= 0){
            errors.rejectValue("maxPeople","maxPeople.positive");
        }


        // -------------------------- Number Of Floor --------------------------------------
        if(service.numberOfFloors == null){
            errors.rejectValue("numberOfFloors", "numberOfFloors.empty");
        } else if (service.numberOfFloors < 0){
            errors.rejectValue("numberOfFloors","numberOfFloors.positive");
        }


        // ------------------------------- Pool Area --------------------------------------
        if(service.poolArea == null){
            errors.rejectValue("poolArea", "poolArea.empty");
        } else if (service.poolArea <= 0){
            errors.rejectValue("poolArea","poolArea.positive");
        }
    }
}
