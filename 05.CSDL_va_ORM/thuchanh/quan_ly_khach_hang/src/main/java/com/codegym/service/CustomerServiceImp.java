//package com.codegym.service;
//
//import com.codegym.entity.Customer;
//import com.codegym.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CustomerServiceImp implements CustomerService {
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Override
//    public List<Customer> findAll() {
//        return this.customerRepository.findAll();
//    }
//
//    @Override
//    public Customer findById(int id) {
//        return this.customerRepository.findById(id);
//    }
//
//    @Override
//    public void save(Customer customer) {
//        this.customerRepository.save(customer);
//    }
//
//    @Override
//    public void update(Customer customer) {
//        this.customerRepository.update(customer);
//    }
//
//    @Override
//    public void remove(int id) {
//        this.customerRepository.remove(id);
//    }
//
//}
