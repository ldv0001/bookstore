package com.ldv0001.service;

import com.ldv0001.Exceptions.UserAlreadyExistException;
import com.ldv0001.model.User;
import com.ldv0001.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpService {

    final UserRepository userRepository;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HttpStatus signup(User user){
        List<User> list = (List)userRepository.findAll();
        boolean userExist =list.stream()
                               .anyMatch(x->x.getUsername()
                                             .equals(user.getUsername()));
        if(userExist){
            throw new UserAlreadyExistException();
        }
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return HttpStatus.OK;
    }

}
