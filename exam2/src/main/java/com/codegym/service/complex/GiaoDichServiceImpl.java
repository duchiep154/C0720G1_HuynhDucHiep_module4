package com.codegym.service.complex;

import com.codegym.entity.complex.Customer;
import com.codegym.entity.complex.GiaoDich;
import com.codegym.entity.complex.LoaiDV;
import com.codegym.repository.complex.CustomerRepository;
import com.codegym.repository.complex.GiaoDichRepository;
import com.codegym.repository.complex.LoaiDVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return this.giaoDichRepository.findAllByLoaiDV_NameContainingAndCustomer_NameContaining(nameSearch, loaiDVSearch);
    }

    @Override
    public List<GiaoDich> findByCustomer_NameContaining(Optional<String> nameSearch) {
        return this.giaoDichRepository.findByCustomer_NameContaining(nameSearch);
//        return null;
    }

    @Override
    public List<GiaoDich> findByLoaiDV_NameContaining(Optional<String> loaiDVSearch) {

        return this.giaoDichRepository.findByLoaiDV_NameContaining(loaiDVSearch);
    }

    @Override
    public List<GiaoDich> findByAllGD(Optional<String> all) {
//        return this.giaoDichRepository.findAllByDienTichOrDonGiaOrNgayGDOrCustomer_NameOrLoaiDV_NameContaining(all);
        return null;


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

//    @Override
//    public List<GiaoDich> search(String serviceType, String keyword) {
//        return this.giaoDichRepository.findAllByLoaiDV_NameContainingAndCustomer_NameContaining(serviceType, keyword);
//    }

    @Override
    public Page<GiaoDich> listAll(int pageNum, Optional<String> keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5);
        String keywordOld="";

        if (!keyword.isPresent()) {
            return giaoDichRepository.findAll(pageable);

        }else {
            keywordOld=keyword.get();
            return giaoDichRepository.search(pageable,keywordOld);

        }

    }

    @Override
    public Page<GiaoDich> findAll(Pageable pageable) {
        return giaoDichRepository.findAll(pageable);
    }

    @Override
    public Page<GiaoDich> findByAllContaining(Pageable pageable, String serviceType, String name) {
        return this.giaoDichRepository.findAllByLoaiDV_NameContainingAndCustomer_NameContaining(pageable, serviceType, name);
    }


}
