package com.ldv0001.service;
import com.ldv0001.model.Basket;
import com.ldv0001.model.Book;
import com.ldv0001.repo.BasketRepository;
import com.ldv0001.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BasketService {

    final BookRepository bookRepository;

    final BasketRepository basketRepository;

    public BasketService(BookRepository bookRepository, BasketRepository basketRepository) {
        this.bookRepository = bookRepository;
        this.basketRepository = basketRepository;
    }

    public  void saveBook(Book bookName, String username){
        Basket basket = new Basket();
        int counterOfTheBooks=0;
        Book book = bookRepository.findById(bookName.getId()).get();

        counterOfTheBooks =basketRepository.findCountOfBooks(book.getId(),username).orElse(0);
        if(counterOfTheBooks ==0){
            basket.setBook(book);
            basket.setUsername(username);
            basket.setPrice(book.getPrice());
            counterOfTheBooks += 1;
            basket.setBooksCount(counterOfTheBooks);
            basketRepository.save(basket);
        }
        else{
            counterOfTheBooks += 1;
            basketRepository.insertIntoCount(counterOfTheBooks,book.getId(),username);
        }
    }

    public HttpStatus deletePosition(long id, String username){
       List<Basket> basket= basketRepository.findForDeletePositionFromBasket(id,username);
       basketRepository.deleteAll(basket);
       return HttpStatus.OK;
    }

    public HttpStatus minusOneBook(long id){
        Basket basket = basketRepository.findById(id).get();
        if(basket.getBooksCount()>1){
            basketRepository.removeOneBook(id);
        }
        else{
            basketRepository.delete(basket);
        }
        return HttpStatus.OK;
    }

    public HttpStatus addOneBook(long id){
        basketRepository.addOneBook(id);
        return HttpStatus.OK;
    }

    public HttpStatus buy(String name){
        List<Basket> list = basketRepository.getBasket(name);
        basketRepository.deleteAll(list);
        return HttpStatus.OK;
    }

}
