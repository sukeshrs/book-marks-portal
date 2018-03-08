package com.spring.test.bookmarks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class BookMarksPortalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BookMarksPortalApplication.class, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BookMarksPortalApplication.class);
    }
}
