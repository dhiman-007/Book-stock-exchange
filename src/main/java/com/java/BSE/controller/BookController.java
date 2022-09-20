package com.java.BSE.controller;

import com.java.BSE.dto.BookDTO;
import com.java.BSE.entity.Author;
import com.java.BSE.entity.Book;
import com.java.BSE.service.BookServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger LOGGER = LogManager.getLogger(BookController.class);

    private BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks() throws Exception {

        LOGGER.info("Inside findAll Book method");

       return new ResponseEntity(this.bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id) {

        LOGGER.info("Inside findBookById for Book id : {}", id);

        return new ResponseEntity(this.bookService.findBookById(id), HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<String>> findBookByYear(@PathVariable int year) throws Exception {

        LOGGER.info("Inside findBookByYear for year : {}", year);

        return new ResponseEntity(this.bookService.findBookByYear(year), HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<String>> findBookByGenre(@PathVariable String genre) throws Exception {

        LOGGER.info("Inside findBookByGenre for genre : {}", genre);

        return new ResponseEntity(this.bookService.findBookByGenre(genre), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) throws Exception {

        LOGGER.info("Inside addBook with book as : {}", bookDTO);

       return new ResponseEntity<Book>(this.bookService.addBook(bookDTO), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Book>> findBookByYearAndGenre(@RequestParam int year, @RequestParam String genre) {

        LOGGER.info("Inside findBookByYearAndGenre with year : {} and genre  : {}", year, genre);

        return new ResponseEntity<List<Book>>(this.bookService.findBookByYearAndGenre(year, genre), HttpStatus.OK);
    }

    @GetMapping("/author/{Genre}")
    public List<Author> findAuthorByGenre(@PathVariable String genre){

        LOGGER.info("Inside findAuthorByGenre with genre  : {}", genre);

        return this.bookService.findAuthorByGenre(genre);
    }

    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable int id){
        return this.bookService.deleteBookById(id);
    }

}
