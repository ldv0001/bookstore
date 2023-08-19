package com.ldv0001.service;

import com.ldv0001.Exceptions.UserAlreadyExistException;
import com.ldv0001.model.Role;
import com.ldv0001.model.User;
import com.ldv0001.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SignUpService {

    @Autowired
    UserRepository userRepository;

    public void signup(User user){
        List<User> list = (List)userRepository.findAll();
        boolean userExist =list.stream()
                               .anyMatch(x->x.getUsername()
                                             .equals(user.getUsername()));
        if(userExist){
            throw new UserAlreadyExistException();
        }
        user.setRole(Role.USER);
        userRepository.save(user);
    }

}
