package com.ldv0001.controllers;

import com.ldv0001.model.Basket;
import com.ldv0001.model.Book;
import com.ldv0001.repo.BasketRepository;
import com.ldv0001.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class BasketController {

    @Autowired
    BasketRepository basketRepository;

    Authentication authentication;

    @Autowired
    BasketService basketService;


    @PostMapping("/basket")
    public void putToBasket(@RequestBody Basket b){
        Book book =b.getBook();
        basketService.saveBook(book,b.getUsername());
    }

    @GetMapping("/basket")
    public List<Basket> getBooksFromBasket(){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        return basketRepository.getBasket(authentication.getName());
    }

    @DeleteMapping("/basket/{id}")
    public void deletePositionFromBasket(@PathVariable long id){
        basketService.deletePosition(id,authentication.getName());
    }

    @DeleteMapping("/basket/removeonebook/{id}")
    public void deleteOneBookFromBasket(@PathVariable long id){
        basketService.minusOneBook(id);
    }

    @DeleteMapping("/basket/buy")
    public void buy(){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        basketService.buy(authentication.getName());
    }

    @PutMapping("/basket/addonebook/{id}")
    public void addOneBookInBasket(@PathVariable long id){
        basketService.addOneBook(id);
    }

}
