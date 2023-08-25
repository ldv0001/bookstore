package com.ldv0001.service;

import com.ldv0001.model.Book;
import com.ldv0001.repo.BasketRepository;
import com.ldv0001.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ImageService imageService;

    public List<Book> find(){
        return (List)bookRepository.findAll();
    }

    public Book findOne(long id){return bookRepository.findById(id).get();}

    public void save(Book book){
        bookRepository.save(book);
    }
    @Transactional
    public void delete(Long id)  {
        boolean bookInBasket = bookRepository.findBookById(id).isPresent();
        if(bookInBasket){
            Book book = bookRepository.findBookById(id).get();
            basketRepository.deleteBooksById(id);
            int result=bookRepository.deleteBookById(id);
            imageService.deleteImage(result,book.getImage());
        }
    }
}
