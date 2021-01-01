package com.codegym.service.complex;

import com.codegym.entity.complex.Customer;
import com.codegym.entity.complex.GiaoDich;
import com.codegym.entity.complex.LoaiDV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GiaoDichService {
//    Page<Product> findByDateImportBetween(Pageable pageable,String dateImport1,String dateImport2);
//    Page<Product> findByDateImportContaining(Pageable pageable,String dateImport1);



    List<GiaoDich> findAll();

    List<GiaoDich> findByCustomer_NameAndLoaiDV_NameContaining( String nameSearch,String loaiDVSearch);

    List<GiaoDich> findByCustomer_NameContaining(Optional<String> nameSearch);

    List<GiaoDich> findByLoaiDV_NameContaining(Optional<String> loaiDVSearch);
    List<GiaoDich> findByAllGD(Optional<String> all);





    void deleteGiaoDich(String id);

    void save(GiaoDich product);

    List<Customer> allCustomer();

    void update(GiaoDich giaoDich);

    GiaoDich findById(String id);

    List<LoaiDV> allLoaiDV();

    Page<GiaoDich> listAll(int pageNum, Optional<String> keyword);




    Page<GiaoDich> findAll(Pageable pageable);
    Page<GiaoDich> findByAllContaining(Pageable pageable,String serviceType,String name);


//    Page<Product> findAllAndSortByID(Pageable pageable);
//
//    Page<Product> findAllAndSortByName(Pageable pageable);

}
