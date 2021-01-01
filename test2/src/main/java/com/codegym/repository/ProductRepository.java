package com.codegym.repository;

import com.codegym.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByDateImportBetween(Pageable pageable,String dateImport1,String dateImport2);
    Page<Product> findByDateImportContaining(Pageable pageable,String dateImport1);
    Page<Product> findByNameContaining(Pageable pageable, String name);

    List<Product> findByNameContaining(String name);

    Page<Product> findByCostContaining(Pageable pageable, String cost);

//    Page<Product> findByNameOrCostOrStatusOrDateImportContaining(Pageable pageable , String all);


    @Query(value = "select * from product where `name` like %?1% or cost like %?1% " +
            "or status like %?1%  or date_import like %?1% ", nativeQuery = true)
    Page<Product> findAllOfMe(Pageable pageable, String all);

    @Query(value = "select * from product where `name` like %?1% and cost like %?2%", nativeQuery = true)
    Page<Product> findByNameAndCostContainingOfMe(Pageable pageable, String name, String cost);

    @Query(value = "select * from product group by id order by id", nativeQuery = true)
    Page<Product> findAllAndSortByID(Pageable pageable);

    @Query(value = "select * from product group by id order by `name`", nativeQuery = true)
    Page<Product> findAllAndSortByName(Pageable pageable);

//    @Query(value = "select * from product group by id order by `status` desc", nativeQuery = true)
//    Page<Product> findAllAndSortByStatus(Pageable pageable);

    @Query(value = "select * from customer limit  ?1, ?2", nativeQuery = true)
    List<Product> findAllScroll(int start, int limit);
}