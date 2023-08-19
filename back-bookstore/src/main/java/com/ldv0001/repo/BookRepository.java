package com.ldv0001.repo;

import com.ldv0001.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {


}
