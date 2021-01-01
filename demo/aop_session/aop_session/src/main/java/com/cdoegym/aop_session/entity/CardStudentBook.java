package com.cdoegym.aop_session.entity;

import javax.persistence.*;

@Entity
@Table(name = "card_student_book")
public class CardStudentBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codeBorrow;

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_card_student")
    private CardStudent cardStudent;

    public CardStudentBook() {
    }

    public CardStudentBook(Long id, Long codeBorrow, Book book, CardStudent cardStudent) {
        this.id = id;
        this.codeBorrow = codeBorrow;
        this.book = book;
        this.cardStudent = cardStudent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeBorrow() {
        return codeBorrow;
    }

    public void setCodeBorrow(Long codeBorrow) {
        this.codeBorrow = codeBorrow;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CardStudent getCardStudent() {
        return cardStudent;
    }

    public void setCardStudent(CardStudent cardStudent) {
        this.cardStudent = cardStudent;
    }
}
