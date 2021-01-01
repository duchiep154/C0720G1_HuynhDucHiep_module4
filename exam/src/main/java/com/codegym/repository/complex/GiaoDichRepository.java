package com.codegym.repository.complex;

import com.codegym.entity.complex.GiaoDich;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich,String> {

//    List<GiaoDich>findAllByCustomer_NameAndLoaiDV_NameContaining(String nameSearch,String loaiDVSearch);


//    List<GiaoDich> findAllByLoaiDV_NameAndCustomer_NameContaining(String nameSearch,String loaiDVSearch);

    List<GiaoDich> findAllByCustomer_NameContaining(String nameSearch);

    List<GiaoDich> findAllByLoaiDV_NameContaining(String loaiDVSearch);
    List<GiaoDich> findAllByLoaiDV_NameAndCustomer_NameContaining( String all);




//    List<GiaoDich> findByCustomer_NameAndLoaiDVContaining(String name);
//
//    Page<Product> findByCostContaining(Pageable pageable, String cost);

//    Page<Product> findByNameOrCostOrStatusOrDateImportContaining(Pageable pageable , String all);


//    @Query(value = "select * from giaodich where `loaiDV` like %?1% or cost like %?1% " +
//            "or status like %?1%  or date_import like %?1% ", nativeQuery = true)
//    Page<Product> findAllOfMe(Pageable pageable, String all);





}
