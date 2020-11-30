package com.codegym.service.customer;

import com.codegym.entity.Customer;
import com.codegym.entity.Provincial;
import com.codegym.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAllByProvincial(Pageable pageable , Optional<Provincial> provincial) {
        return customerRepository.findAllByProvincial(pageable,provincial);
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(int id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void remove(int id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> findByNameContaining(Pageable pageable, String name) {
        return this.customerRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

//    @Override
//    public void remove(String name) {
//        List<Customer> customerList = this.customerRepository.findAllByNameContaining(name);
//        this.customerRepository.deleteAll(customerList);
//    }
}
