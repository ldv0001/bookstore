package com.ldv0001.repo;

import com.ldv0001.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("select (b.nameOfTheBook) from Book b")
//    int existByBook();
    Optional<Book> existByBook();

    @Transactional
    @Modifying
    @Query("delete from Book b where b.id =?1")
    int deleteBookById(Long id);

    @Query("from Book b where b.id = ?1")
    Optional<Book> findBookById(Long id);
}
