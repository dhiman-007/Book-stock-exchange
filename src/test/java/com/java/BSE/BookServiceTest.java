package com.java.BSE;

import com.java.BSE.dto.BookDTO;
import com.java.BSE.entity.Author;
import com.java.BSE.entity.Book;
import com.java.BSE.exceptions.ResourceNotFoundException;
import com.java.BSE.repository.AuthorRepository;
import com.java.BSE.repository.BookRepository;
import com.java.BSE.service.AuthorServiceImpl;
import com.java.BSE.service.BookServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    static  List<Book> books = Arrays.asList(
            new Book(1, "Pacman", 1987, "Magic"),
            new Book(2, "IKIGAI", 2010, "Health & life"),
            new Book(3, "Love And Wander", 2012, "Love")
    );

    static List<Author> authors = Arrays.asList(
            new Author(1, "Shubham"),
            new Author(2, "Aayush")
    );

    @Test
    public void findAllBooksTest() throws Exception {
        when(bookRepository.findAll()).thenReturn(books);
        Assert.assertEquals(books, bookService.findAllBooks());
    }


    @Test
    public void findBookByIdTest() throws Exception {
        final int authorId = 1;

        when(bookRepository.findById(authorId))
                .thenReturn(
                        Optional.ofNullable(
                        books.stream()
                        .filter(f -> f.getBookId() == authorId)
                        .collect(Collectors.toList()).get(0))
                )  ;

        Assert.assertEquals(books.get(0), bookService.findBookById(1));
    }

    @Test
    public void addBookTest() throws Exception {
        final BookDTO book = new BookDTO(4, "HoolaHoo", 1896, "Adventure", 1);

        when(authorRepository.findById(book.getAuthorId()))
                .thenReturn(
                        Optional.ofNullable(
                        authors.stream()
                        .filter(f -> f.getAuthorId() == book.getAuthorId())
                        .collect(Collectors.toList())
                        .get(0)
                        )
                );

        Book b = new Book();
        b.setBookId(book.getBookId());
        b.setGenre(book.getGenre());
        b.setTitle(book.getTitle());
        b.setYear(book.getYear());
        b.setAuthor(
                authors.stream()
                .filter(f -> f.getAuthorId() == book.getAuthorId())
                .collect(Collectors.toList())
                .get(0)
        );

        when(bookRepository.save(b)).thenReturn(b);

        Assert.assertEquals(b, bookService.addBook(book));

    }


    @Test
    public void deleteBookById() throws Exception {
            final int bookId = 1;

        when(bookRepository.findById(bookId))
                .thenReturn(
                        Optional.ofNullable(books.stream()
                                .filter(b ->b.getBookId() == bookId)
                                .collect(Collectors.toList())
                                .get(0))
                );

        Book book = bookRepository.findById(bookId).get();
    }



}
