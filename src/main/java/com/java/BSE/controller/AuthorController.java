 package com.java.BSE.controller;

import com.java.BSE.entity.Author;
import com.java.BSE.service.AuthorServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private static final Logger LOGGER = LogManager.getLogger(AuthorController.class);

    private AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAllAuthor() {

        LOGGER.info("Inside findAll Author method");

        List<Author> authors = this.authorService.findAllAuthor();

        LOGGER.debug("All Authors found : {} ", authors);

        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {

        LOGGER.info("Inside addAuthor with author:  {}" , author);

        Author auth = this.authorService.addAuthor(author);

        LOGGER.debug("Author saved successfully : {} " , auth);

        return new ResponseEntity<>(auth, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {

        LOGGER.info("Inside getAuthorById for authorId : {}", id);

        Author author = this.authorService.getAuthorById(id);

        LOGGER.debug("Found Author with authorId : {}", id);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {

        LOGGER.info("Inside deleteAuthor for authorId : {}", id);

        this.authorService.deleteAuthor(id);

        LOGGER.debug("Author deleted with authorId : {}", id);

        return new
                ResponseEntity<String>(
                        "Author with " + id + " deleted successfully",
                        HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id,
                                               @RequestBody Author updatedAuthor) {

        LOGGER.info("Inside updateAuthor with authorId : {} and author : {}", id, updatedAuthor);

        Author author = this.authorService.updateAuthor(id, updatedAuthor);

        LOGGER.debug("Author {} updated with {}", author, updatedAuthor);

        return new ResponseEntity<Author>(author, HttpStatus.OK);

    }
}
