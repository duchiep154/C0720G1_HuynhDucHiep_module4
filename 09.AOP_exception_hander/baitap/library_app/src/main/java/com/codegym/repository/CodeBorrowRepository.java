package com.codegym.repository;

import com.codegym.entity.CodeBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeBorrowRepository extends JpaRepository<CodeBorrow, Integer> {

    List<CodeBorrow> findCodeByBook_Id(Integer id);
    List<CodeBorrow> findCodeByBook_IdAndStatus_Id(Integer bookId, Integer statusId);
}
