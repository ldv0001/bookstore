package com.ldv0001.controllers;

import com.ldv0001.model.Book;
import com.ldv0001.repo.UserRepository;
import com.ldv0001.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public List<Book> getPage(){
        return  bookService.find();
    }

    @GetMapping("/{id}")
    public Book getOneBook(@PathVariable long id){
        Book book = bookService.findOne(id);
        return book;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.delete(id);
    }

}

