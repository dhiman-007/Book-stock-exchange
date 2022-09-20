package com.java.BSE.service;

import com.java.BSE.entity.Author;
import com.java.BSE.exceptions.ResourceNotFoundException;
import com.java.BSE.repository.AuthorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOGGER = LogManager.getLogger(AuthorServiceImpl.class);

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> findAllAuthor() {
        LOGGER.info("Inside findAllAuthor method");
        return this.authorRepository.findAll();
    }


    @Override
    public Author addAuthor(Author author) {

        LOGGER.info("Inside addAuthor method with author : {}", author);

        return this.authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(Integer id) {

        LOGGER.info("Inside getAuthorById method for authorId : {}", id);

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return author;
    }

    @Override
    public void deleteAuthor(Integer id) {

        LOGGER.info("Inside deleteAuthor method for authorId : {}", id);

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));

        this.authorRepository.delete(author);

    }

    @Override
    public Author updateAuthor(Integer id, Author updatedAuthor) {

        LOGGER.info("Inside updateAuthor method with authorId : {} and author : {}", id, updatedAuthor);

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));

        author.setAuthorName(updatedAuthor.getAuthorName());

        return this.authorRepository.save(updatedAuthor);

    }
}
