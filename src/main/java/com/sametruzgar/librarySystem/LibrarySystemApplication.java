package com.sametruzgar.librarySystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
public class LibrarySystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(LibrarySystemApplication.class, args);

	}

}
@RestController
class HomeController{
	@GetMapping("/")
	public String home(){
		return "Kütüphane Sistemine Hoşgeldiniz";
	}
}
