package com.java.BSE.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.BSE.entity.Author;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class BookDTO {

    private int bookId;
    private String title;
    private int year;
    private String genre;
    private int authorId;

    public BookDTO(int bookId, String title, int year, String genre, int authorId) {
        this.bookId = bookId;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.authorId = authorId;
    }
}
