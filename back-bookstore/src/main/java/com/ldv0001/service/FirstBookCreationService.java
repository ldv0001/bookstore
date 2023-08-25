package com.ldv0001.service;

import com.ldv0001.model.Author;
import com.ldv0001.model.Book;
import com.ldv0001.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FirstBookCreationService {

    String name = "Java: Complete reference";
    String authorsName = "Herbert Schildt";
    String description = "Fully updated for Java SE 9, Java: The Complete Reference, " +
            "Tenth Edition explains how to develop, compile, debug, and run Java programs." +
            " Bestselling programming author Herb Schildt covers the entire Java language," +
            " including its syntax, keywords, and fundamental programming principles. " +
            "You'll also find information on key portions of the Java API library, such as I/O," +
            " the Collections Framework, the stream library, and the concurrency utilities." +
            " Swing, JavaFX, JavaBeans, and servlets are examined and numerous examples demonstrate " +
            "Java in action. Of course, the new module system added by Java SE 9 is discussed in" +
            " detail. This Oracle Press resource also offers an introduction to JShell, " +
            "Java’s new interactive programming tool.";
    float price = 1067.8f;
    String image ="java_schildt.jpg";

    @Autowired
    BookRepository repository;

    public void create() throws IOException {
        boolean bookExist = repository.existByBook().isPresent();
        if (!bookExist){
            copyImage();
            Book book = new Book(name,image,description,price,new Author(authorsName));
            repository.save(book);
        }
    }

    void copyImage() throws IOException {
        Path source = Paths.get("/app/"+image);
        Path destination = Paths.get("/app/images/"+image);
        Files.copy(source,destination, StandardCopyOption.REPLACE_EXISTING);
    }
}
