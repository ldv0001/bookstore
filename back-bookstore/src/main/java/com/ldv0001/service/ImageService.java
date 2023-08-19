package com.ldv0001.service;

import com.ldv0001.model.Book;
import com.ldv0001.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    @Autowired
    BookRepository bookRepository;
    public void saveImage(MultipartFile file){
            Path filePath = Paths.get("/app/images",file.getOriginalFilename());

        try {
            Files.write(filePath,file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getImage(long id) throws IOException {
        Book book = bookRepository.findById(id).get();
        String image = book.getImage();
        Path filePath = Paths.get("/app/images/"+image);
        return Files.readAllBytes(filePath);
    }

}
