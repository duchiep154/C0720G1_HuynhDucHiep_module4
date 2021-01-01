package com.cdoegym.aop_session.service;

import com.cdoegym.aop_session.entity.CardStudentBook;

public interface CardStudentBookService {
    void save(CardStudentBook cardStudentBook);

    CardStudentBook findByCodeBorrow(Long codeBorrow);
}
