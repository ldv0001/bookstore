package com.ldv0001.repo;

import com.ldv0001.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User,Long> {
        User findByUsername(String username);

        @Query("select count(u.role) from User u where u.role = 'ADMIN' ")
        Optional<User> existByRole();


}
