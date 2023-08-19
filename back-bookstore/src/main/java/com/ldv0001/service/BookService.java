package com.ldv0001.service;

import com.ldv0001.model.Book;
import com.ldv0001.repo.BasketRepository;
import com.ldv0001.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BasketRepository basketRepository;

    public List<Book> find(){
        return (List)bookRepository.findAll();
    }

    public Book findOne(long id){return bookRepository.findById(id).get();}

    public void save(Book book){
        bookRepository.save(book);
    }

    public void delete(Long id)  {

        boolean bookInBasket = basketRepository.findBookById(id).isPresent();
        if(bookInBasket){
            Book book = basketRepository.findBookById(id).get();
            Path path = Paths.get("/app/images/",book.getImage());
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            basketRepository.deleteBooksById(id);
        }

        bookRepository.deleteById(id);
    }
}
