package com.cdoegym.aop_session.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameBook;

    private Long amount;

    @OneToMany(mappedBy = "book")
    private List<CardStudentBook> cardStudentBookList;

    public Book(Long id, String nameBook, Long amount, List<CardStudentBook> cardStudentBookList) {
        this.id = id;
        this.nameBook = nameBook;
        this.amount = amount;
        this.cardStudentBookList = cardStudentBookList;
    }

    public Book() {
    }

    public List<CardStudentBook> getCardStudentBookList() {
        return cardStudentBookList;
    }

    public void setCardStudentBookList(List<CardStudentBook> cardStudentBookList) {
        this.cardStudentBookList = cardStudentBookList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
