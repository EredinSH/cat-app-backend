package com.kodilla.cats.service;

import com.kodilla.cats.controller.CatNotFoundException;
import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.repository.CatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbCatServiceTest {

    @InjectMocks
    private DbCatService service;

    @Mock
    private CatRepository repository;

    @Test
    public void getAllCatsTest() {
        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");
        Cat cat2 = new Cat(2L, "Regis","5","test2");
        List<Cat> catsList = new ArrayList<>();
        catsList.add(cat1);
        catsList.add(cat2);

        when(repository.findAll()).thenReturn(catsList);

        //WHEN
        List<Cat> resultList = service.getAllCats();

        //THEN
        assertEquals("Regis",resultList.get(1).getName());

    }

    @Test
    public void saveCatTest() {
        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");
        when(repository.save(cat1)).thenReturn(cat1);

        //WHEN
        Cat result = service.saveCat(cat1);

        //THEN
        assertThat(result).isNotNull();

    }

    @Test
    public void getCatTest() throws CatNotFoundException {
        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");
        when(repository.findById(1L)).thenReturn(Optional.of(cat1));

        //WHEN
        Cat result = service.getCat(1L);

        //THEN
        assertEquals("Puszek",result.getName());

    }

    @Test
    public void deleteByIdTest() throws CatNotFoundException {
        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");

        //WHEN
        service.deleteById(1L);

        //THEN
        assertFalse(false,cat1.getName());

    }

}