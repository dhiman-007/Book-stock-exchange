package com.java.BSE.repository;

import com.java.BSE.entity.Author;
import com.java.BSE.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select s from Book s where s.year = ?1 AND s.genre = ?2")
    List<Book> findByYearAndGenre(int year, String genre);

    @Query("select distinct s.author from Book s where s.genre =?1")
    List<Author> findAuthorByGenre(String genre);
}
