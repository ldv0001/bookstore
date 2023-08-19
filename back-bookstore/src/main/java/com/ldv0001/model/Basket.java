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

    int countOfTheBooks;

    float price;

    public Long getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCountOfTheBooks() {
        return countOfTheBooks;
    }

    public void setCountOfTheBooks(int countOfTheBooks) {
        this.countOfTheBooks = countOfTheBooks;
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
