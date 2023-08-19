package com.ldv0001.service;

import com.ldv0001.model.Role;
import com.ldv0001.model.User;
import com.ldv0001.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCreationService {
    @Autowired
    UserRepository usr;

    public void create(){
        int user = usr.existByRole();
		if(user==0){
            usr.save(new User("admin","root", Role.ADMIN));
		}
    }
}
