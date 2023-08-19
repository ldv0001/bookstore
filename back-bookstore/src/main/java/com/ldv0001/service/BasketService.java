package com.ldv0001.service;

import com.ldv0001.model.Basket;
import com.ldv0001.model.Book;
import com.ldv0001.repo.BasketRepository;
import com.ldv0001.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BasketService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BasketRepository basketRepository;

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
            basket.setCountOfTheBooks(counterOfTheBooks);
            basketRepository.save(basket);
        }
        else{
            counterOfTheBooks += 1;
            basketRepository.insertIntoCount(counterOfTheBooks,book.getId(),username);
            basketRepository.insertIntoPrice(counterOfTheBooks * book.getPrice(),book.getId(),username);
        }
    }

    public void deletePosition(long id,String username){
       List<Basket> basket= basketRepository.findForDeletePositionFromBasket(id,username);
       basketRepository.deleteAll(basket);
    }

    public void minusOneBook(long id){
        Basket basket = basketRepository.findById(id).get();
        if(basket.getCountOfTheBooks()>1){
            basketRepository.removeOneBook(id);
        }
        else{
            basketRepository.delete(basket);
        }
    }

    public void addOneBook(long id){
        basketRepository.addOneBook(id);
    }

    public void buy(String name){
        List<Basket> list = basketRepository.getBasket(name);
        basketRepository.deleteAll(list);
    }

}
