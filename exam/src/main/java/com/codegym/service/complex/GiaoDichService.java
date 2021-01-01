package com.codegym.service.complex;

import com.codegym.entity.complex.Customer;
import com.codegym.entity.complex.GiaoDich;
import com.codegym.entity.complex.LoaiDV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GiaoDichService {
//    Page<Product> findByDateImportBetween(Pageable pageable,String dateImport1,String dateImport2);
//    Page<Product> findByDateImportContaining(Pageable pageable,String dateImport1);



    List<GiaoDich> findAll();

    List<GiaoDich> findByCustomer_NameAndLoaiDV_NameContaining( String nameSearch,String loaiDVSearch);

    List<GiaoDich> findByCustomer_NameContaining(String nameSearch);

    List<GiaoDich> findByLoaiDV_NameContaining(String loaiDVSearch);
    List<GiaoDich> findByLoaiDV_NameOrCustomer_Name( String all);





    void deleteGiaoDich(String id);

    void save(GiaoDich product);

    List<Customer> allCustomer();

    void update(GiaoDich giaoDich);

    GiaoDich findById(String id);

    List<LoaiDV> allLoaiDV();


//    Page<GiaoDich> findByLoaiDVContaining(Pageable pageable, String keywordTwoOld);
//    Page<GiaoDich> findByCustomer_NameContaining(Pageable pageable,String name);


//    Page<Product> findAllAndSortByID(Pageable pageable);
//
//    Page<Product> findAllAndSortByName(Pageable pageable);

}
