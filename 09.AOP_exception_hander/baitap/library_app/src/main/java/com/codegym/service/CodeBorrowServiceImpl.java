package com.codegym.service;

import com.codegym.entity.CodeBorrow;
import com.codegym.repository.CodeBorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeBorrowServiceImpl implements CodeBorrowService {

    @Autowired
    CodeBorrowRepository codeBorrowRepository;

    @Override
    public List<CodeBorrow> findAll() {
        return codeBorrowRepository.findAll();
    }

    @Override
    public CodeBorrow findById(Integer id) {
        return codeBorrowRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CodeBorrow codeBorrow) {
        codeBorrowRepository.save(codeBorrow);
    }

    @Override
    public List<CodeBorrow> findAllCodeByBookId(Integer id) {
        return codeBorrowRepository.findCodeByBook_Id(id);
    }

    @Override
    public List<CodeBorrow> findAvailableCodeByBookId(Integer id) {
        return codeBorrowRepository.findCodeByBook_IdAndStatus_Id(id, 1);
    }

    @Override
    public List<CodeBorrow> findUsedCodeByBookId(Integer id) {
        return codeBorrowRepository.findCodeByBook_IdAndStatus_Id(id, 2);
    }
}
