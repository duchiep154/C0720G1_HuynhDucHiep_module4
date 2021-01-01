package com.codegym.controllers.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.CustomerType;
import com.codegym.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restCustomerController")
@CrossOrigin
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<List<Customer>> getListCustomer() {
        List<Customer> listCustomer = this.customerService.findAll();
        return new ResponseEntity<>(listCustomer, HttpStatus.OK);
    }

    @GetMapping("/scroll")
    public ResponseEntity<List<Customer>> getListCustomerScroll(@RequestParam int start, int limit) {
        List<Customer> listCustomer = this.customerService.findAllScroll(start, limit);
        return new ResponseEntity<>(listCustomer, HttpStatus.OK);
    }

    @PostMapping("/search/{name}")
    public ResponseEntity<List<Customer>> getCustomer(@PathVariable String name) {
        List<Customer> listCustomer = this.customerService.findByNameContainingAjax(name);
        return new ResponseEntity<>(listCustomer, HttpStatus.OK);
    }

    @PostMapping("/createNewCustomer/{id}/{name}/{birthDay}/{gender}/{salary}/{idCard}/" +
            "{phoneNumber}/{email}/{address}/{customerType}")
    public ResponseEntity<List<Customer>> createNewCustomer(@PathVariable String id,
                                                            @PathVariable String name,
                                                            @PathVariable String birthDay,
                                                            @PathVariable String gender,
                                                            @PathVariable String salary,
                                                            @PathVariable String idCard,
                                                            @PathVariable String phoneNumber,
                                                            @PathVariable String email,
                                                            @PathVariable String address,
                                                            @PathVariable String customerType) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setBirthDay(birthDay);
        customer.setGender(gender);
        customer.setSalary(Double.parseDouble(salary));
        customer.setIdCard(idCard);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setAddress(address);
        CustomerType customerTypeAdd = new CustomerType();
        customerTypeAdd.setId(1);
        customerTypeAdd.setName(customerType);
        customer.setCustomerType(customerTypeAdd);
        this.customerService.save(customer);
        List<Customer> listCustomer = this.customerService.findAll();
        return new ResponseEntity<>(listCustomer, HttpStatus.OK);
    }
}
