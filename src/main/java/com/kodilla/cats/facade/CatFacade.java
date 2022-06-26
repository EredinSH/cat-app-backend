package com.kodilla.cats.facade;

import com.kodilla.cats.controller.CatNotFoundException;
import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.domain.CatDto;
import com.kodilla.cats.mapper.CatMapper;
import com.kodilla.cats.service.DbCatService;
import com.kodilla.cats.validator.CatValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CatFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatFacade.class);
    private final DbCatService service;
    private final CatMapper mapper;
    private final CatValidator validator;

    public List<CatDto> getCats() {
        List<Cat> cats = service.getAllCats();
        List<Cat> filteredCats = validator.validateCatList(cats);
        return mapper.mapToCatDtoList(filteredCats);
    }

    public CatDto getCat(Long catId) throws CatNotFoundException {
        validator.validateCat(service.getCat(catId));
        return mapper.mapToCatDto(service.getCat(catId));
    }

    public void addCat(CatDto catDto) {
        Cat cat = mapper.mapToCat(catDto);
        validator.validateCat(cat);
        service.saveCat(cat);
    }

    public CatDto updateCat(CatDto catDto) {
        Cat cat = mapper.mapToCat(catDto);
        validator.validateCat(cat);
        Cat savedCat = service.saveCat(cat);
        return mapper.mapToCatDto(savedCat);
    }

    public void deleteCat(Long catId) throws CatNotFoundException {
        service.deleteById(catId);
    }

}
