package com.ldv0001.controllers;

import com.ldv0001.model.Book;
import com.ldv0001.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AdminController {

    @Autowired
    BookService bookService;

    @PostMapping("/admin")
    public void postMethod(@RequestBody Book b){
        bookService.save(b);
    }
}
