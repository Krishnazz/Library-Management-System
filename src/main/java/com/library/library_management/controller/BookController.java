package com.library.library_management.controller;

import com.library.library_management.dto.BookRequest;
import com.library.library_management.entity.Book;
import com.library.library_management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private final BookService bookService;

    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public ResponseEntity<?> getBooks(){
//        if(bookService.getAllBooks().isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    @PostMapping("/api/books")
    public ResponseEntity<?> addBook(@RequestBody @Valid BookRequest bookRequest){
        Book book=bookService.convertToBook(bookRequest);
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }
    @GetMapping("/api/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id ){
        Book book=  bookService.getBookById(id);
//        if(book!=null){
//            return new ResponseEntity<>(book,HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(book,HttpStatus.OK);

    }
    @PutMapping("/api/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest){
        Book book=bookService.convertToBook(bookRequest);
        Book updatedBook=bookService.updateBook(id, book);
      /*  if(updatedBook!=null){
            return new ResponseEntity<>(updatedBook,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
        return new ResponseEntity<>(updatedBook,HttpStatus.OK);
    }
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){

        if(bookService.deleteBookById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
