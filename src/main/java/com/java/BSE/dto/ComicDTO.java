package com.java.BSE.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.BSE.entity.Author;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
public class ComicDTO {


    private int comicId;
    private String title;
    private int year;
    private String heroName;
    private int authorId;

}
