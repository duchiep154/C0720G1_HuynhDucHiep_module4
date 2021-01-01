package com.codegym.service.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.CustomerType;
import com.codegym.repository.customer.CustomerRepository;
import com.codegym.repository.customer.CustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllAndSortByID(Pageable pageable) {
        return this.customerRepository.findAllAndSortByID(pageable);
    }

    @Override
    public Page<Customer> findAllAndSortByName(Pageable pageable) {
        return this.customerRepository.findAllAndSortByName(pageable);
    }

    @Override
    public Page<Customer> findAllAndSortByBirthDay(Pageable pageable) {
        return this.customerRepository.findAllAndSortByBirthDay(pageable);
    }

    @Override
    public Page<Customer> findByNameContaining(Pageable pageable, String name) {
        return this.customerRepository.findByNameContaining(pageable, name);
    }

    @Override
    public List<Customer> findByNameContainingAjax(String name) {
        return this.customerRepository.findByNameContaining(name);
    }

    @Override
    public Page<Customer> findByAddressContaining(Pageable pageable, String address) {
        return this.customerRepository.findByAddressContaining(pageable, address);
    }

    @Override
    public Page<Customer> findByNameAndAddressContaining(Pageable pageable, String name, String address) {
        return this.customerRepository.findByNameAndAddressContainingOfMe(pageable, name, address);
    }

    @Override
    public Page<Customer> findAllOfMe(Pageable pageable, String all) {
        return this.customerRepository.findAllOfMe(pageable, all);
    }

    @Override
    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public List<CustomerType> allCustomerType() {
        return this.customerTypeRepository.findAll();
    }

    @Override
    public void update(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllScroll(int start, int limit) {
        return this.customerRepository.findAllScroll(start, limit);
    }

    @Override
    public Customer findById(String id) {
        return this.customerRepository.findById(id).orElse(null);
    }
}
