package com.kodilla.cats.controller;

import com.google.gson.Gson;
import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.domain.CatDto;
import com.kodilla.cats.facade.CatFacade;
import com.kodilla.cats.mapper.CatMapper;
import com.kodilla.cats.service.DbCatService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(CatController.class)
public class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatFacade facade;


    @Test
    public void shouldGetCatsTest() throws Exception {

        //GIVEN
        List<CatDto> catDtoList = List.of(new CatDto(1L, "Puszek","4","test"));

        when(facade.getCats()).thenReturn(catDtoList);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Puszek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age", is("4")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", is("test")));

    }

    @Test
    public void shouldGetCatTest() throws Exception {

        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");
        CatDto catDto1 = new CatDto(1L, "Puszek","4","test");

        when(facade.getCat(cat1.getId())).thenReturn(catDto1);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cat/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Puszek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", is("4")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("test")));

    }

    @Test
    public void addCatTest() throws Exception {

        //GIVEN
        CatDto catDto1 = new CatDto(1L, "Puszek","4","test");

        facade.addCat(catDto1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(catDto1);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateCatTest() throws Exception {

        //GIVEN
        CatDto updatedCatDto1 = new CatDto(2L, "Puszek","5","test2");

        when(facade.updateCat(any())).thenReturn(updatedCatDto1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedCatDto1);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Puszek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", is("5")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("test2")))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldDeleteCatTest() throws Exception {

        //GIVEN
        Cat cat1 = new Cat(1L, "Puszek","4","test");

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/cat/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
