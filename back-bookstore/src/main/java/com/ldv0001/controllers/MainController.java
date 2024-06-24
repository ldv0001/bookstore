package com.ldv0001.controllers;

import com.ldv0001.model.AuthenticationResponse;
import com.ldv0001.model.Basket;
import com.ldv0001.model.Book;
import com.ldv0001.model.User;
import com.ldv0001.repo.BasketRepository;
import com.ldv0001.repo.UserRepository;
import com.ldv0001.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class MainController {

    final UserRepository userRepository;

    final BookService bookService;

    final LoginService loginService;

    final BasketRepository basketRepository;

    Authentication authentication;

    final BasketService basketService;

    final ImageService imageService;

    final SignUpService signUpService;

    final JwtUtil jwtUtil;

    public MainController(UserRepository userRepository, BookService bookService, LoginService loginService, BasketRepository basketRepository, BasketService basketService, ImageService imageService, SignUpService signUpService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.loginService = loginService;
        this.basketRepository = basketRepository;
        this.basketService = basketService;
        this.imageService = imageService;
        this.signUpService = signUpService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getPage(){
        List<Book> books = bookService.find();
        return  new ResponseEntity<>(books,HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<String> postMethod(@RequestBody Book b){
        HttpStatus status =bookService.save(b);
        return  new ResponseEntity<>("book saved",status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOneBook(@PathVariable long id){
        Book book = bookService.findOne(id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id){
        bookService.delete(id);
        return new ResponseEntity<>("book deleted",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody User user){
        AuthenticationResponse response = loginService.login(user);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("/basket")
    public ResponseEntity<String> putToBasket(@RequestBody Basket b){
        Book book =b.getBook();
        basketService.saveBook(book,b.getUsername());
        return new ResponseEntity<>("book added to basket", HttpStatus.OK);
    }

    @GetMapping("/basket")
    public ResponseEntity<List<Basket>> getBooksFromBasket(){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Basket>baskets = basketRepository.getBasket(authentication.getName());
        return new ResponseEntity<>(baskets,HttpStatus.OK);
    }

    @DeleteMapping("/basket/{id}")
    public ResponseEntity<String> deletePositionFromBasket(@PathVariable long id){
        HttpStatus status =basketService.deletePosition(id,authentication.getName());
        return new ResponseEntity<>("position deleted",status);
    }

    @DeleteMapping("/basket/removeonebook/{id}")
    public ResponseEntity<String> deleteOneBookFromBasket(@PathVariable long id){
        HttpStatus status =basketService.minusOneBook(id);
        return new ResponseEntity<>("remove one book",status);
    }

    @DeleteMapping("/basket/buy")
    public ResponseEntity<String> buy(){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpStatus status= basketService.buy(authentication.getName());
        return new ResponseEntity<>("all books bought",status);
    }

    @PutMapping("/basket/addonebook/{id}")
    public ResponseEntity<String> addOneBookInBasket(@PathVariable long id){
        HttpStatus status =basketService.addOneBook(id);
        return new ResponseEntity<>("book added",status);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<String> saveImage(@RequestParam("imageFile") MultipartFile file) {
        HttpStatus status = imageService.saveImage(file);
        return new ResponseEntity<>("image saved",status);
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable long id) throws IOException {
        return imageService.getImage(id);
    }


    @GetMapping("/refresh")
    public ResponseEntity<String> refreshToken(HttpServletRequest request, HttpServletResponse response){
        AuthenticationResponse authResponse = jwtUtil.refreshToken(request);
        return new ResponseEntity(authResponse,HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<String > signUp(@RequestBody User user){
        HttpStatus status =signUpService.signup(user);
        return new ResponseEntity<>("user added",status);
    }

}

