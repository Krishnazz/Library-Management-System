package com.library.library_management.controller;

import com.library.library_management.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

   private final BookService bookService;

    HelloController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/hello")
    public String hello(){

        return "Library Management System is running!";
    }
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
         return "Hello, "+name+"!";
    }

}
