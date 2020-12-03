package com.codegym.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "describe_book")
    @NotBlank
    private String describeBook;

    @Column(name = "author")
    @NotBlank
    private String author;

    @Column(name = "quantity")
    @NotNull
    @Min(0)
    private Integer quantity;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CodeBorrow> codeBorrowSet = new LinkedHashSet<>();

    public void generateCode(CodeBorrow code) {
        codeBorrowSet.add(code);
    }

    public void borrow() {
        if (quantity > 0) quantity--;
    }

    public void returnBook() {
        quantity++;
    }
}