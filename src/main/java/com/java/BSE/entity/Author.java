package com.java.BSE.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.sun.istack.NotNull;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    private int authorId;

    @OneToMany(mappedBy = "author")
    List<Book> bookList;

    @OneToMany(mappedBy = "author")
    private List<Comic> comics;

    private String authorName;

    public Author(){

    }

    public Author(int authorId, String authorName){
        this.authorId = authorId;
        this.authorName = authorName;
    }

}
