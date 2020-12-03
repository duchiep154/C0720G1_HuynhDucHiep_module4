package com.codegym.service;

import com.codegym.entity.CodeBorrow;

import java.util.List;

public interface CodeBorrowService {
    List<CodeBorrow> findAll();

    CodeBorrow findById(Integer id);

    void save(CodeBorrow codeBorrow);

    List<CodeBorrow> findAllCodeByBookId(Integer id);

    List<CodeBorrow> findAvailableCodeByBookId(Integer id);

    List<CodeBorrow> findUsedCodeByBookId(Integer id);
}
