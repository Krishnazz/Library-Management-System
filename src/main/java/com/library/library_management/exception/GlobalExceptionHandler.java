package com.library.library_management.exception;

import com.library.library_management.dto.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundExceptionException(BookNotFoundException e){
        errorResponse errorResponse = new errorResponse(
                LocalDateTime.now(),404,"Not Found",e.getMessage()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericExceptionException(Exception e){
        errorResponse errorResponse = new errorResponse(
                LocalDateTime.now(),500,"Internal Server error","Something went wrong"
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
