package com.kodilla.cats.controller;

import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.domain.CatDto;
import com.kodilla.cats.facade.CatFacade;
import com.kodilla.cats.mapper.CatMapper;
import com.kodilla.cats.service.DbCatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cat")
@CrossOrigin("*")
public class CatController {

    @Autowired
    private final CatFacade facade;

    public CatController(CatFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public ResponseEntity<List<CatDto>> getCats() {
        return ResponseEntity.ok(facade.getCats());
    }

    @GetMapping(value = "{catId}")
    public ResponseEntity<CatDto> getCat(@PathVariable Long catId) throws CatNotFoundException {
        return ResponseEntity.ok(facade.getCat(catId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCat(@RequestBody CatDto catDto) {
        facade.addCat(catDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CatDto> updateCat(@RequestBody CatDto catDto) {
        return ResponseEntity.ok(facade.updateCat(catDto));
    }

    @DeleteMapping(value = "{catId}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long catId) throws CatNotFoundException {
        facade.deleteCat(catId);
        return ResponseEntity.ok().build();
    }

}
