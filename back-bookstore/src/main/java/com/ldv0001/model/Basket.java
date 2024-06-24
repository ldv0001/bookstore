package com.ldv0001.model;

import javax.persistence.*;
import javax.persistence.Entity;


@Entity
public class Basket {
    public Basket(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    int booksCount;

    double price;

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(int countOfTheBooks) {
        this.booksCount = countOfTheBooks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne()
    Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
