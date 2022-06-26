package com.kodilla.cats.facade;

import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.domain.CatDto;
import com.kodilla.cats.mapper.CatMapper;
import com.kodilla.cats.service.DbCatService;
import com.kodilla.cats.validator.CatValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CatFacadeTest {

    @InjectMocks
    private CatFacade facade;

    @Mock
    private DbCatService service;

    @Mock
    private CatMapper mapper;

    @Mock
    private CatValidator validator;

    @Test
    public void shouldGetCatsTest() throws Exception {

        //GIVEN
        List<Cat> catList = List.of(new Cat(1L, "Puszek","4","test"));

        List<CatDto> catDtoList = List.of(new CatDto(1L, "Puszek","4","test"));

        when(service.getAllCats()).thenReturn(catList);
        when(validator.validateCatList(catList)).thenReturn(catList);
        when(mapper.mapToCatDtoList(catList)).thenReturn(catDtoList);

        //WHEN
        List<CatDto> result = facade.getCats();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

        result.forEach(cats -> {

            assertThat(cats.getId()).isEqualTo(1L);
            assertThat(cats.getName()).isEqualTo("Puszek");
            assertThat(cats.getAge()).isEqualTo("4");
            assertThat(cats.getDescription()).isEqualTo("test");

        });

    }

    @Test
    public void shouldGetCatTest() throws Exception {

        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");
        CatDto catDto1 = new CatDto(1L, "Puszek","4","test");

        when(service.getCat(1L)).thenReturn(cat1);
        validator.validateCat(cat1);
        when(mapper.mapToCatDto(cat1)).thenReturn(catDto1);

        //WHEN
        CatDto result = facade.getCat(1L);

        // THEN
        assertThat(result).isNotNull();
        assertEquals(1l,result.getId());
        assertEquals("Puszek",result.getName());
        assertEquals("4",result.getAge());
        assertEquals("test",result.getDescription());

    }

    @Test
    public void addCatTest() throws Exception {

        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");
        CatDto catDto1 = new CatDto(1L, "Puszek","4","test");

        when(mapper.mapToCat(catDto1)).thenReturn(cat1);
        validator.validateCat(cat1);
        when(service.saveCat(cat1)).thenReturn(cat1);

        //WHEN
        facade.addCat(catDto1);

        // THEN
        assertThat(catDto1).isNotNull();
        assertEquals(1l,catDto1.getId());
        assertEquals("Puszek",catDto1.getName());
        assertEquals("4",catDto1.getAge());
        assertEquals("test",catDto1.getDescription());

    }

    @Test
    public void shouldUpdateCatTest() throws Exception {

        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");
        CatDto updatedCatDto1 = new CatDto(2L, "Puszek","5","test2");

        when(mapper.mapToCat(updatedCatDto1)).thenReturn(cat1);
        validator.validateCat(cat1);
        when(service.saveCat(cat1)).thenReturn(new Cat(2L, "Puszek","5","test2"));
        when(mapper.mapToCatDto(any())).thenReturn(updatedCatDto1);

        //WHEN
        CatDto result = facade.updateCat(updatedCatDto1);

        // THEN
        assertThat(result).isNotNull();
        assertEquals(2l,result.getId());
        assertEquals("Puszek",result.getName());
        assertEquals("5",result.getAge());
        assertEquals("test2",result.getDescription());


    }

    @Test
    public void shouldDeleteCatTest() throws Exception {

        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");

        service.deleteById(1L);

        //WHEN && THEN
        facade.deleteCat(1L);

    }

}