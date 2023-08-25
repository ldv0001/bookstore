package com.ldv0001.repo;

import com.ldv0001.model.Basket;
import com.ldv0001.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

//    @Query("from Book b where b.id = ?1")
//    Optional<Book> findBookById(Long id);

    @Transactional
    @Modifying
    @Query("delete from Basket bs where bs.book.id =?1")
    int deleteBooksById(Long id);

    @Query("select bs.countOfTheBooks from Basket bs where bs.book.id =?1 and bs.username = ?2")
    Optional<Integer> findCountOfBooks(Long id, String username);

    @Transactional
    @Modifying
    @Query("update Basket  bs set bs.countOfTheBooks = ?1 where bs.book.id = ?2 and bs.username =?3 ")
    void insertIntoCount(int counter, Long id,String username);

//    @Transactional
//    @Modifying
//    @Query("update Basket  bs set bs.price = ?1 where bs.book.id = ?2 and bs.username =?3 ")
//    void insertIntoPrice(float price, Long id, String username);

    @Query("from Basket b where b.username = ?1")
    List<Basket> getBasket(String username);

    @Query("select bs from Basket bs where bs.id =?1 and bs.username =?2")
    List<Basket> findForDeletePositionFromBasket(long id, String username);

    @Transactional
    @Modifying
    @Query("update Basket  bs set bs.countOfTheBooks = (bs.countOfTheBooks - 1) where bs.id = ?1")
    void removeOneBook(long id);

    @Transactional
    @Modifying
    @Query("update Basket  bs set bs.countOfTheBooks = (bs.countOfTheBooks + 1) where bs.id = ?1")
    void addOneBook(long id);

}

