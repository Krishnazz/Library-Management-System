package com.library.library_management.service;

import com.library.library_management.dto.BookRequest;
import com.library.library_management.dto.BookResponse;
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

    public BookResponse addBook(BookRequest bookRequest){
       return convertToBookResponse(bookRepository.save(convertToBook(bookRequest)));
    }
    public List<BookResponse> getAllBooks(){
       return bookRepository.findAll().stream().map(i->convertToBookResponse(i)).toList();
    }
    public BookResponse getBookById(Long id){
        return convertToBookResponse(findBookOrThrow(id));
    }
    public BookResponse updateBook(Long id,BookRequest book){


        Book existingBook=findBookOrThrow(id);
        //if(existingBook!=null){
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setAvailable(book.isAvailable());
            existingBook.setPublishedYear(book.getPublishedYear());
            return convertToBookResponse(bookRepository.save(existingBook));
       // }
      //return null;
    }
    public void deleteBookById(Long id){


       /* if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }*/
        bookRepository.deleteById(id);

    }
    private Book findBookOrThrow(Long id){
        return bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Book with id " + id + " does not exist"));
    }

    private Book convertToBook(BookRequest bookRequest){
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setAvailable(bookRequest.isAvailable());
        book.setPublishedYear(bookRequest.getPublishedYear());
        return book;
    }
    private BookResponse convertToBookResponse(Book book){

        return new BookResponse(
                book.getId(),
                book.getTitle(), book.getAuthor(), book.getIsbn(),
                book.getPublishedYear(),book.isAvailable()
        );
    }
}
