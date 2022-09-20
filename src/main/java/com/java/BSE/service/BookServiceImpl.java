package com.java.BSE.service;

import com.java.BSE.dto.BookDTO;
import com.java.BSE.entity.Author;
import com.java.BSE.entity.Book;
import com.java.BSE.exceptions.ResourceNotFoundException;
import com.java.BSE.repository.AuthorRepository;
import com.java.BSE.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAllBooks() throws Exception {

        LOGGER.info("Inside findAll Book method");

        List<Book> books;
        try {
            books = this.bookRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

        return books;
    }

    @Override
    public Book findBookById(int id) {

        LOGGER.info("Inside findById for Book id : {}", id);

        return this.bookRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
    }

    @Override
    public List<String> findBookByYear(int year) throws Exception {

        LOGGER.info("Inside findBookByYear for year : {}", year);

        List<Book> books;
        try {
            books = this.bookRepository
                    .findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return books
                .stream()
                .filter(f -> f.getYear() == year)
                .map(m -> m.getTitle())
                .collect(Collectors.toList());

    }

    @Override
    public List<String> findBookByGenre(String genre) throws Exception {

        LOGGER.info("Inside findBookByGenre for genre : {}", genre);

        List<Book> books;
        try {
            books = this.bookRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return books
                .stream()
                .filter(f -> f.getGenre().equalsIgnoreCase(genre))
                .map(g -> g.getGenre())
                .collect(Collectors.toList());

    }

    @Override
    public Book addBook(BookDTO book) throws Exception {

        LOGGER.info("Inside addBook with book as : {}", book);

        Author author = this.authorRepository
                .findById(book.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", book.getAuthorId()));

        Book b = new Book();
        b.setBookId(book.getBookId());
        b.setGenre(book.getGenre());
        b.setTitle(book.getTitle());
        b.setYear(book.getYear());
        b.setAuthor(author);

        this.bookRepository.save(b);

        LOGGER.debug("Book saved Successfully : {}", b);

        return b;

    }

    @Override
    public List<Book> findBookByYearAndGenre(int year, String genre) {

        LOGGER.info("Inside findBookByYearAndGenre with year : {} and genre  : {}", year, genre);

        return this.bookRepository.findByYearAndGenre(year, genre);
    }

    @Override
    public List<Author> findAuthorByGenre(String genre) {

        LOGGER.info("inside findAuthorByGenre for genre : {}", genre);

        return this.bookRepository.findAuthorByGenre(genre);
    }


    @Override
    public String deleteBookById(int id) {
        Book book = this.bookRepository.findById(id).get();
        if(book != null) {
            this.bookRepository.delete(book);
            return "Book Deleted SUccessfully ";
        }
        return "Book not found with id :" + id;
    }
}


