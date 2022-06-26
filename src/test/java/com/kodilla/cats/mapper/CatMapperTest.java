package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.domain.CatDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatMapperTest {

    @Mock
    CatMapper mapper;

    @Test
    public void mapToCatTest() {

        //GIVEN
        CatDto catDto = new CatDto(1L, "Puszek","4","test");
        when(mapper.mapToCat(catDto)).thenReturn(new Cat(1L, "Puszek","4","test"));

        //WHEN
        Cat cat = mapper.mapToCat(catDto);

        //THEN
        assertThat(cat).isNotNull();
        assertEquals(1l,cat.getId());
        assertEquals("Puszek",cat.getName());
        assertEquals("4",cat.getAge());
        assertEquals("test",cat.getDescription());

    }

    @Test
    public void mapToCatDtoTest() {

        //GIVEN
        Cat cat = new Cat(1L, "Puszek","4","test");
        when(mapper.mapToCatDto(cat)).thenReturn(new CatDto(1L, "Puszek","4","test"));

        //WHEN
        CatDto catDto = mapper.mapToCatDto(cat);

        //THEN
        assertThat(catDto).isNotNull();
        assertEquals(1l,catDto.getId());
        assertEquals("Puszek",catDto.getName());
        assertEquals("4",catDto.getAge());
        assertEquals("test",catDto.getDescription());

    }

    @Test
    public void mapToCatDtoListTest() {

        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");
        Cat cat2 = new Cat(2L, "Regis","5","test2");
        List<Cat> catList = new ArrayList<>();
        catList.add(cat1);
        catList.add(cat2);

        List<CatDto> catDtoList = List.of(new CatDto(1L, "Puszek","4","test"),(new CatDto(2L, "Regis","5","test2")));

        when(mapper.mapToCatDtoList(catList)).thenReturn(catDtoList);

        //WHEN
        List<CatDto> result = mapper.mapToCatDtoList(catList);

        //THEN
        assertEquals(2,result.size());
        assertEquals("Regis",result.get(1).getName());

    }

}