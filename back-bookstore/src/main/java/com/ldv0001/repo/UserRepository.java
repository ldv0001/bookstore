package com.ldv0001.repo;

import com.ldv0001.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@Repository
public interface UserRepository extends CrudRepository<User,Long> {
        User findByUsername(String username);

        @Query("select count(u.role) from User u where u.role = 'ADMIN' ")
//        int existByRole();
        Optional<User> existByRole();


}
