package com.library.library_management;

import com.library.library_management.entity.Book;
import com.library.library_management.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
	}

//    @Bean
//    CommandLineRunner run(BookService bookService) {
//        return args -> {
//            bookService.addBook(new Book(null, "Clean Code", "Robert Martin", "9780132350884", 2008, true));
//            bookService.addBook(new Book(null, "Effective Java", "Joshua Bloch", "9780134685991", 2018, true));
//            bookService.getAllBooks().forEach(System.out::println);
//        };
//    }

}

