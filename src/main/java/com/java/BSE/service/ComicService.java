package com.java.BSE.service;

import com.java.BSE.dto.BookDTO;
import com.java.BSE.dto.ComicDTO;
import com.java.BSE.entity.Author;
import com.java.BSE.entity.Book;
import com.java.BSE.entity.Comic;

import java.util.List;

public interface ComicService {

    List<Comic> findAllComics() throws Exception;

    Comic findComicById(int id);

    List<String> findComicByYear(int year) throws Exception;

    List<Comic> findComicByHeroName(String heroName) throws Exception;

    Comic addComic(ComicDTO comicDTO) throws Exception;

    List<Comic> findComicByYearAndHeroName(int year, String heroName);

    List<Author> findAuthorByYearAndGenre(int year, String genre);

}
