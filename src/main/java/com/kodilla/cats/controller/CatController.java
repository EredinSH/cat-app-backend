package com.kodilla.cats.controller;

import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.domain.CatDto;
import com.kodilla.cats.mapper.CatMapper;
import com.kodilla.cats.service.DbCatService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cat")
@CrossOrigin("*")
public class CatController {

    private final DbCatService service;
    private final CatMapper mapper;

    public CatController(DbCatService service, CatMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CatDto>> getCats() {
        List<Cat> cats = service.getAllCats();
        return ResponseEntity.ok(mapper.mapToCatDtoList(cats));
    }

    @GetMapping(value = "{catId}")
    public ResponseEntity<CatDto> getCat(@PathVariable Long catId) throws CatNotFoundException {
        return ResponseEntity.ok(mapper.mapToCatDto(service.getCat(catId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCat(@RequestBody CatDto catDto) {
        Cat cat = mapper.mapToCat(catDto);
        service.saveCat(cat);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CatDto> updateCat(@RequestBody CatDto catDto) {
        Cat cat = mapper.mapToCat(catDto);
        Cat savedCat = service.saveCat(cat);
        return ResponseEntity.ok(mapper.mapToCatDto(savedCat));
    }

    @DeleteMapping(value = "{catId}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long catId) {
        return ResponseEntity.ok().build();
    }

}
