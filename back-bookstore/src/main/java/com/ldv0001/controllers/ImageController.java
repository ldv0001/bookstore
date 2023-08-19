package com.ldv0001.controllers;

import com.ldv0001.repo.BookRepository;
import com.ldv0001.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ImageService imageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public void saveImage(@RequestParam("imageFile") MultipartFile file) {
        imageService.saveImage(file);
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable long id) throws IOException {
        return imageService.getImage(id);
    }

}

