package com.ldv0001.model;

import javax.persistence.*;

@Entity
public class Book {
    public Book(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nameOfTheBook;

    String image;

    public Book(String nameOfTheBook, String image, String description, float price, Author author) {
        this.nameOfTheBook = nameOfTheBook;
        this.image = image;
        this.description = description;
        this.price = price;
        this.author = author;
    }

    @Column(columnDefinition = "TEXT")
    String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNameOfTheBook() {
        return nameOfTheBook;
    }

    public void setNameOfTheBook(String nameOfTheBook) {
        this.nameOfTheBook = nameOfTheBook;
    }

    public Long getId() {
        return id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    Author author;

}
