package com.ldv0001.service;

import com.ldv0001.model.Book;
import com.ldv0001.repo.BasketRepository;
import com.ldv0001.repo.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    final BookRepository bookRepository;
    final BasketRepository basketRepository;
    final ImageService imageService;

    public BookService(BookRepository bookRepository, BasketRepository basketRepository, ImageService imageService) {
        this.bookRepository = bookRepository;
        this.basketRepository = basketRepository;
        this.imageService = imageService;
    }

    public List<Book> find(){
        return (List)bookRepository.findAll();
    }

    public Book findOne(long id){return bookRepository.findById(id).get();}

    public HttpStatus save(Book book){
        bookRepository.save(book);
        return HttpStatus.OK;
    }


    @Transactional
    public HttpStatus delete(Long id) {
        boolean bookInBasket = bookRepository.findBookById(id).isPresent();
        if (bookInBasket) {
            Book book = bookRepository.findBookById(id).get();
            basketRepository.deleteBooksById(id);
            int result = bookRepository.deleteBookById(id);
            try {
                imageService.deleteImage(result, book.getImage());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return HttpStatus.OK;
    }
}
