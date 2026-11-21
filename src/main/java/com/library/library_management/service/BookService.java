package com.library.library_management.service;

import com.library.library_management.entity.Book;
import com.library.library_management.exception.BookNotFoundException;
import com.library.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
       return bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
       return bookRepository.findAll();
    }
    public Book getBookById(Long id){
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException("Book with id " + id + " does not exist");
        }
        return bookRepository.findById(id).orElse(null);
    }
    public Book updateBook(Long id,Book book){


        Book existingBook=bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book with id " + id + " does not exist"));
        //if(existingBook!=null){
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setAvailable(book.isAvailable());
            existingBook.setPublishedYear(book.getPublishedYear());
            return bookRepository.save(existingBook);
       // }
      //return null;
    }
    public boolean deleteBookById(Long id){

        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException("Book with id " + id + " does not exist");
        }
       /* if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }*/
        bookRepository.deleteById(id);
        return true;
    }
}
