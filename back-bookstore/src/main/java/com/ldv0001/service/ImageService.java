package com.ldv0001.service;

import com.ldv0001.model.Book;
import com.ldv0001.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    final BookRepository bookRepository;

    @Value("${ldv.imagepath}")
    String imagepath;

    public ImageService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public HttpStatus saveImage(MultipartFile file){
            Path filePath = Paths.get(imagepath,file.getOriginalFilename());
        try {
            Files.write(filePath,file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }

    public byte[] getImage(long id) throws IOException {
        Book book = bookRepository.findById(id).get();
        String image = book.getImage();
        Path filePath = Paths.get(imagepath+image);
        return Files.readAllBytes(filePath);
    }

    public void deleteImage(int deleteResult, String nameOfTheFile){
        Path path = Paths.get(imagepath,nameOfTheFile);
        if(deleteResult >0) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
