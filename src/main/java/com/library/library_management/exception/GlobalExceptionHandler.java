package com.library.library_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundExceptionException(BookNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),404,"Not Found",e.getMessage()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
     /*
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),400,"INVALID INPUT",e.getFieldError().getDefaultMessage()
        );  /// if 3 fields comes with invalid also it will only return the first one so we go for alternate,

        */
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((field)->errors.put(field.getField(),field.getDefaultMessage()));

        Map<String,Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("timestamp",LocalDateTime.now());
        errorResponse.put("status",400);
        errorResponse.put("error","Bad Request");
        errorResponse.put("errors",errors);

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericExceptionException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),500,"Internal Server error","Something went wrong"
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
