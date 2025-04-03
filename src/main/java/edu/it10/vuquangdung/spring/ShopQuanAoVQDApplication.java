package edu.it10.vuquangdung.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ShopQuanAoVQDApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopQuanAoVQDApplication.class, args);
	}

}
