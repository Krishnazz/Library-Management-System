package com.library.library_management.dto;

 
import java.time.LocalDateTime;

public record errorResponse (
         LocalDateTime timestamp,
        int status,
        String error,
        String msg
){}