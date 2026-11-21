package com.library.library_management.controller;

import com.library.library_management.dto.BookRequest;
import com.library.library_management.entity.Book;
import com.library.library_management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody @Valid BookRequest bookRequest){
        Book book=bookService.convertToBook(bookRequest);
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id ){
        Book book=  bookService.getBookById(id);
            return new ResponseEntity<>(book,HttpStatus.OK);

    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest){
        Book book=bookService.convertToBook(bookRequest);
        Book updatedBook=bookService.updateBook(id, book);
        return new ResponseEntity<>(updatedBook,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){

        if(bookService.deleteBookById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
