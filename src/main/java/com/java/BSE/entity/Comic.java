 package com.java.BSE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "comics")
public class Comic {

    @Id
    private int comicId;

    private String title;
    private String genre;
    private int year;
    private String heroName;

    @ManyToOne
    @JsonIgnore
    private Author author;


}
