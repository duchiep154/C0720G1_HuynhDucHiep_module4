package com.codegym.repository.complex;

import com.codegym.entity.complex.GiaoDich;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich,String> {
    List<GiaoDich> findAllByLoaiDV_NameContainingAndCustomer_NameContaining(String serviceType, String customerName);

    Page<GiaoDich> findAllByLoaiDV_NameContainingAndCustomer_NameContaining(Pageable pageable, String serviceType, String customerName);

    List<GiaoDich> findByLoaiDV_NameContaining(Optional<String> loaiDVSearch);

    List<GiaoDich> findAll();
    List<GiaoDich> findByCustomer_NameContaining(Optional<String> name);

    Page<GiaoDich> findAll(Pageable pageable);


    @Query("SELECT g FROM GiaoDich g WHERE g.customer.name LIKE %?1%"
            + " OR g.loaiDV.name LIKE %?1%"

            + " OR CONCAT(g.dienTich, '') LIKE %?1%")
    Page<GiaoDich> search(Pageable pageble , String keyword);

}
