package com.java.BSE.service;

import com.java.BSE.dto.BookDTO;
import com.java.BSE.entity.Author;
import com.java.BSE.entity.Book;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface BookService {

    List<Book> findAllBooks() throws Exception;

    Book findBookById(int id);

    List<String> findBookByYear(int year) throws Exception;

    List<String> findBookByGenre(String genre) throws Exception;

    Book addBook(BookDTO book) throws Exception;

    List<Book> findBookByYearAndGenre(int year, String genre);

    List<Author> findAuthorByGenre(String Genre);

    String deleteBookById(int id);
}
