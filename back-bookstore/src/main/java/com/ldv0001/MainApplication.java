package com.ldv0001;

import com.ldv0001.service.AdminCreationService;
import com.ldv0001.service.FirstBookCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.io.IOException;


@SpringBootApplication

public class MainApplication {

	@Autowired
	AdminCreationService adminCreationService;
	@Autowired
	FirstBookCreationService bookCreationService;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		adminCreationService.create();
		bookCreationService.create();
	}
}
