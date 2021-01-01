package com.cdoegym.aop_session.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "card_student")
public class CardStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cardStudent")
    private List<CardStudentBook> cardStudentBookList;

    public CardStudent() {
    }

    public CardStudent(Long id, String name, List<CardStudentBook> cardStudentBookList) {
        this.id = id;
        this.name = name;
        this.cardStudentBookList = cardStudentBookList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CardStudentBook> getCardStudentBookList() {
        return cardStudentBookList;
    }

    public void setCardStudentBookList(List<CardStudentBook> cardStudentBookList) {
        this.cardStudentBookList = cardStudentBookList;
    }
}
