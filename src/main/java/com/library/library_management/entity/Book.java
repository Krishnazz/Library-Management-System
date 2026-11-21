package com.library.library_management.entity;

//id (Long, primary key, auto-generated)
//title (String)
//author (String, kept simple for now; we’ll make it a proper entity in Phase 4)
//isbn (String)
//publishedYear (int)
//available (boolean)

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
    private boolean available;
}

