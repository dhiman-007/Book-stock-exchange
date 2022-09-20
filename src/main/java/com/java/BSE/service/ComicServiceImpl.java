package com.java.BSE.service;

import com.java.BSE.dto.ComicDTO;
import com.java.BSE.entity.Author;
import com.java.BSE.entity.Book;
import com.java.BSE.entity.Comic;
import com.java.BSE.exceptions.ResourceNotFoundException;
import com.java.BSE.repository.AuthorRepository;
import com.java.BSE.repository.BookRepository;
import com.java.BSE.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComicServiceImpl implements ComicService{

    private ComicRepository comicRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public ComicServiceImpl(ComicRepository comicRepository, AuthorRepository authorRepository){
        this.comicRepository = comicRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Comic> findAllComics() throws Exception {
        List<Comic> comics;
        try{
            comics = this.comicRepository.findAll();
        }
        catch (Exception e){
            throw new Exception(e);
        }
        return comics;
    }

    @Override
    public Comic findComicById(int id) {
        return this.comicRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comic", "id" , id));
    }

    @Override
    public List<String> findComicByYear(int year) throws Exception {
        List<Comic> comics;
        try {
            comics = this.comicRepository
                    .findAll();
        }
        catch (Exception e){
            throw new Exception(e);
        }
        return comics
                .stream()
                .filter(f -> f.getYear() == year)
                .map(m -> m.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<Comic> findComicByHeroName(String heroName) throws Exception {
//        System.out.println(heroName);
//        List<Comic> comics;
//        try {
//            comics = this.comicRepository.findAll();
//        }
//        catch (Exception e){
//            throw new Exception(e);
//        }
//        System.out.println(comics.get(0).toString());
//        return comics
//                .stream()
//                .filter(f -> f.getHeroName().equalsIgnoreCase(heroName))
//                .collect(Collectors.toList());
        return this.comicRepository.findByHeroName(heroName);
    }

    @Override
    public Comic addComic(ComicDTO comicDTO) throws Exception {
        Author author = this.authorRepository
                .findById(comicDTO.getComicId())
                .orElseThrow(() -> new ResourceNotFoundException("Comic", "id", comicDTO.getComicId()));

        Comic comic = new Comic();
        comic.setComicId(comicDTO.getComicId());
        comic.setTitle(comicDTO.getTitle());
        comic.setYear(comicDTO.getYear());
        comic.setHeroName(comicDTO.getHeroName());
        comic.setAuthor(author);

        this.comicRepository.save(comic);

        return comic;
    }

    @Override
    public List<Comic> findComicByYearAndHeroName(int year, String heroName) {
        return this.comicRepository.findByYearAndheroName(year, heroName);
    }

    public List<Author> findAuthorByYearAndGenre(int year, String genre) {
        return this.comicRepository.findByYearAndGenre(year, genre);
    }
}
