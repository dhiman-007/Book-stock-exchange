package com.java.BSE.service;


import com.java.BSE.entity.Author;
import com.java.BSE.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface AuthorService {

    List<Author> findAllAuthor();

    Author addAuthor(Author author);

    Author getAuthorById(Integer id);

    void deleteAuthor(Integer id);

    Author updateAuthor(Integer id, Author author);

}
