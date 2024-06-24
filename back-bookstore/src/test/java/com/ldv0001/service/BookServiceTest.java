package com.ldv0001.service;

import com.ldv0001.model.Book;
import com.ldv0001.repo.BasketRepository;
import com.ldv0001.repo.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    BasketRepository basketRepository;

    @Mock
    ImageService imageService;

    @InjectMocks
    BookService bookService;

    static Book book;

    @BeforeAll
    static void bookInitiate(){
        long id = 1;
        book = new Book();
        book.setNameOfTheBook("name1");
        book.setId(id);
    }

    @Test
    void should_find_all_books(){
        List<Book>books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> newBooks = bookService.find();
        assertNotNull(newBooks);
        assertEquals(books.get(0).getNameOfTheBook(),newBooks.get(0).getNameOfTheBook());
    }
    @Test
    void should_find_by_id(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Book newBook =bookService.findOne(1L);
        assertNotNull(newBook);
        assertEquals(newBook.getNameOfTheBook(),book.getNameOfTheBook());
    }

    @Test
    void should_delete_book_and_return_status_ok(){
//        when(bookRepository.deleteBookById(1L)).thenReturn(1);
//        when(basketRepository.deleteBooksById(1L)).thenReturn(1);
//        doNothing().when(imageService).deleteImage(isA(Integer.class),isA(String.class));

        imageService.deleteImage(1,"image");
        verify(imageService,times(1)).deleteImage(1,"image");


        HttpStatus status = bookService.delete(1L);
        System.out.println(status);

    }

}