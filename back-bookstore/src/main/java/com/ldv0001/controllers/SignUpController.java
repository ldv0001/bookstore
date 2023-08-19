package com.ldv0001.controllers;

import com.ldv0001.model.User;
import com.ldv0001.repo.UserRepository;
import com.ldv0001.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SignUpController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SignUpService signUpService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody User user){
        signUpService.signup(user);
    }
}
