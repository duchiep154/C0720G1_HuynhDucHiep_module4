package com.codegym.service.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.TypeCustomer;
import com.codegym.entity.login.AppUser;
import com.codegym.repository.customer.CustomerRepository;
import com.codegym.repository.customer.TypeCustomerRepository;
import com.codegym.repository.login.AppUserRepository;
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
    private TypeCustomerRepository typeCustomerRepository;

    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Customer findById(String id) {
        return this.customerRepository.findById(id).orElse(null);
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
    public void remove(String id) {
        this.customerRepository.deleteById(id);

    }

    @Override
    public List<TypeCustomer> findAllTypeCustomer() {
        return this.typeCustomerRepository.findAll();
    }

    @Override
    public List<AppUser> findAllAppUser() {
        return this.appUserRepository.findAll();
    }

    @Override
    public Page<Customer> findByNameContaining(Pageable pageable, String name) {
        return this.customerRepository.findAllByFullNameContaining(name, pageable);
    }
}
