package com.library.library_management.exception;

 
import java.time.LocalDateTime;

public record ErrorResponse(
         LocalDateTime timestamp,
        int status,
        String error,
        String message
){}