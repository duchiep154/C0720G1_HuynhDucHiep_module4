package com.codegym.service.complex;

import com.codegym.entity.complex.Customer;
import com.codegym.entity.complex.GiaoDich;
import com.codegym.entity.complex.LoaiDV;
import com.codegym.repository.complex.CustomerRepository;
import com.codegym.repository.complex.GiaoDichRepository;
import com.codegym.repository.complex.LoaiDVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiaoDichServiceImpl implements GiaoDichService {


    @Autowired
    private GiaoDichRepository giaoDichRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LoaiDVRepository loaiDVRepository;

    @Override
    public List<GiaoDich> findAll() {
        return this.giaoDichRepository.findAll() ;
    }

    @Override
    public List<GiaoDich> findByCustomer_NameAndLoaiDV_NameContaining( String nameSearch, String loaiDVSearch) {
        return null;
    }

    @Override
    public List<GiaoDich> findByCustomer_NameContaining( String nameSearch) {
        return this.giaoDichRepository.findAllByCustomer_NameContaining(nameSearch);
    }

    @Override
    public List<GiaoDich> findByLoaiDV_NameContaining( String loaiDVSearch) {
        return this.giaoDichRepository.findAllByLoaiDV_NameContaining(loaiDVSearch);
    }

    @Override
    public List<GiaoDich> findByLoaiDV_NameOrCustomer_Name( String all) {
        return this.giaoDichRepository.findAllByLoaiDV_NameAndCustomer_NameContaining(all);
    }



    @Override
    public void deleteGiaoDich(String id) {
        this.giaoDichRepository.deleteById(id);

    }

    @Override
    public void save(GiaoDich giaoDich) {
        this.giaoDichRepository.save(giaoDich);

    }

    @Override
    public List<Customer> allCustomer() {
        return this.customerRepository.findAll();
    }

    @Override
    public void update(GiaoDich giaoDich) {
        this.giaoDichRepository.save(giaoDich);

    }

    @Override
    public GiaoDich findById(String id) {
        return this.giaoDichRepository.findById(id).orElse(null);
    }

    @Override
    public List<LoaiDV> allLoaiDV() {
        return this.loaiDVRepository.findAll();
    }



}
