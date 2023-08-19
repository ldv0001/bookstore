package com.ldv0001;

import com.ldv0001.service.AdminCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;


@SpringBootApplication

public class MainApplication {

	@Autowired
	AdminCreationService adminCreationService;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@PostConstruct
	public void init(){
		adminCreationService.create();
	}
}
