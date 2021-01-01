package com.cdoegym.aop_session.repository;

import com.cdoegym.aop_session.entity.Book;
import com.cdoegym.aop_session.entity.CardStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardStudentRepository extends JpaRepository<CardStudent, Long> {
}
