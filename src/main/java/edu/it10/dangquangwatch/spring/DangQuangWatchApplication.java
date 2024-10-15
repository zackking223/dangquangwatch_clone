package edu.it10.dangquangwatch.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DangQuangWatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DangQuangWatchApplication.class, args);
	}

}
