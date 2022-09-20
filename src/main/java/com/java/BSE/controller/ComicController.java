package com.java.BSE.controller;

import com.java.BSE.dto.ComicDTO;
import com.java.BSE.entity.Comic;
import com.java.BSE.service.ComicServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {


    private static final Logger LOGGER = LogManager.getLogger(ComicController.class);

    private ComicServiceImpl comicService;

    public ComicController(ComicServiceImpl comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public ResponseEntity<List<Comic>> findAllComics() throws Exception {

        LOGGER.info("Inside findAll Comics");

        return new ResponseEntity(this.comicService.findAllComics(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comic> findComicById(@PathVariable int id) {

       LOGGER.info("Inside findComicById for Comic id : {}", id);

        return new ResponseEntity(this.comicService.findComicById(id), HttpStatus.OK);
    }

    @GetMapping("/hero/{heroName}")
    public ResponseEntity<List<Comic>> findComicByHeroName(@PathVariable String heroName) throws Exception {

        LOGGER.info("Inside findComicByHeroName for HeroName: {}", heroName);

        return new ResponseEntity(this.comicService.findComicByHeroName(heroName), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Comic> addComic(@RequestBody ComicDTO comicDTO) throws Exception {

        LOGGER.info("Inside addComic with comic : {}", comicDTO);

        return new ResponseEntity<Comic>(this.comicService.addComic(comicDTO), HttpStatus.CREATED);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<String>> findComicByYear(@PathVariable int year) throws Exception {

        LOGGER.info("Inside findComicByYear for year : {}", year);

        return new ResponseEntity<List<String>>(this.comicService.findComicByYear(year), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<java.util.List<Comic>> findComicByYearAndHeroName(@RequestParam int year,
                                                                            @RequestParam String heroName) {

        LOGGER.info(
                "Inside findComicByYearAndHeroName with year : {} and heroName",
                year,
                heroName
        );

        return new ResponseEntity<java.util.List<Comic>>(this.comicService.findComicByYearAndHeroName(year, heroName), HttpStatus.OK);
    }

}

// 01324004262
