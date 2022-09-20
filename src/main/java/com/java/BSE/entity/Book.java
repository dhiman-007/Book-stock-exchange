package com.java.BSE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    private int bookId;

    private String title;
    private int year;
    private String genre;

    @ManyToOne
    @JsonIgnore
    private Author author;

    public Book(){

    }

    public Book(int bookId, String title, int year, String genre) {
        this.bookId = bookId;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }
}
