package com.java.BSE.repository;

import com.java.BSE.entity.Author;
import com.java.BSE.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Integer> {

    @Query("select s from Comic s where s.year = ?1 AND s.heroName = ?2")
    List<Comic> findByYearAndheroName(int year, String heroName);

    @Query("select s from Comic s where s.heroName = ?1")
    List<Comic> findByHeroName(String heroName);

    @Query("select distinct s.author from Comic s where s.year = ?1 AND s.genre = ?2")
    List<Author> findByYearAndGenre(int year, String genre);
}
