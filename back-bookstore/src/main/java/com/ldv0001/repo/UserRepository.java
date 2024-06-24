package com.ldv0001.repo;

import com.ldv0001.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {
        User findByUsername(String username);
}
