package com.ldv0001.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    public Author(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    Set<Book> books = new HashSet<>();

    public String getName() {
        return name;
    }

    public Author(String name, Book book) {
        this.name = name;
        books.add(book);
    }

    public Author(String name) {
        this.name = name;
    }
}
