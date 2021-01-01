package com.cdoegym.aop_session.service.impl;

import com.cdoegym.aop_session.entity.CardStudentBook;
import com.cdoegym.aop_session.repository.CardStudentBookRepository;
import com.cdoegym.aop_session.service.CardStudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardStudentBookServiceImpl implements CardStudentBookService {
    @Autowired
    private CardStudentBookRepository cardStudentBookRepository;

    @Override
    public void save(CardStudentBook cardStudentBook) {
        cardStudentBook.setStatus(false);
        cardStudentBookRepository.save(cardStudentBook);
    }

    @Override
    public CardStudentBook findByCodeBorrow(Long codeBorrow) {
        CardStudentBook cardStudentBook = cardStudentBookRepository.findByCodeBorrow(codeBorrow);
        return cardStudentBook;
    }
}
