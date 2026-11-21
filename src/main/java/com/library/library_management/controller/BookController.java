package com.library.library_management.controller;

import com.library.library_management.dto.BookRequest;
import com.library.library_management.dto.BookResponse;
import com.library.library_management.entity.Book;
import com.library.library_management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBooks(){
      return bookService.getAllBooks();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse addBook(@RequestBody @Valid BookRequest request){
        return bookService.addBook(request);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBook(@PathVariable Long id ){
        return  bookService.getBookById(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest){
          return bookService.updateBook(id, bookRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
    }
}
