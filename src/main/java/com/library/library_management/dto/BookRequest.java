package com.library.library_management.dto;

import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class BookRequest {
    @NotBlank(message = "title is required")
    @Size(message = "title length must be at most 100 characters", min = 1, max = 100)
    private String title;
    @NotBlank(message="author is required")
    private String author;
    @NotBlank(message="isbn is required")
    @Pattern(regexp="^(\\d{10}|\\d{13})$", message = "ISBN must be 10 or 13 digits")
    private String isbn;
    //@NotBlank(message="publishedYear is required")
    //@Range(message="publishedYear should be 1997-2026", min=1997, max=2026)
    @Min(value = 1890,message ="publishedYear should 1890 or later")
    @Max(value = 2100,message ="publishedYear should 2100 or earlier")
    private int publishedYear;
    private boolean available;
}
