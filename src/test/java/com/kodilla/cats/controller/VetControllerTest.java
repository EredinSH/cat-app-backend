package com.kodilla.cats.controller;

import com.google.gson.Gson;
import com.kodilla.cats.domain.Vet;
import com.kodilla.cats.domain.VetDto;
import com.kodilla.cats.mapper.VetMapper;
import com.kodilla.cats.service.DbVetService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

@AutoConfigureMockMvc
@SpringJUnitWebConfig
@WebMvcTest(VetController.class)
class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbVetService service;

    @MockBean
    private VetMapper mapper;


    @Test
    public void shouldGetVetsTest() throws Exception {

        //GIVEN
        List<Vet> vetList = List.of(new Vet(1L,"Smile","Kraków"));

        List<VetDto> vetDtoList = List.of(new VetDto(1L,"Smile","Kraków"));

        when(service.getAllVets()).thenReturn(vetList);
        when(mapper.mapToVetDtoList(vetList)).thenReturn(vetDtoList);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/vet")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Smile")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location", is("Kraków")));

    }

    @Test
    public void shouldGetVetTest() throws Exception {

        //GIVEN
        Vet vet = new Vet(1L,"Smile","Kraków");
        VetDto vetDto = new VetDto(1L,"Smile","Kraków");

        when(service.getVet(1L)).thenReturn(vet);
        when(mapper.mapToVetDto(vet)).thenReturn(vetDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/vet/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Smile")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", is("Kraków")));

    }

    @Test
    public void addVetTest() throws Exception {

        //GIVEN
        Vet vet = new Vet(1L,"Smile","Kraków");
        VetDto vetDto = new VetDto(1L,"Smile","Kraków");

        when(mapper.mapToVet(vetDto)).thenReturn(vet);
        when(service.saveVet(vet)).thenReturn(new Vet(1L,"Smile","Kraków"));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(vetDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/vet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateVetTest() throws Exception {

        //GIVEN
        Vet vet = new Vet(1L,"Smile","Kraków");
        VetDto vetDto = new VetDto(1L,"Smile","Kraków");
        VetDto updatedVetDto = new VetDto(2L,"Smile and Fun","Kraków");

        when(mapper.mapToVet(vetDto)).thenReturn(vet);
        when(service.saveVet(vet)).thenReturn(new Vet(2L,"Smile and Fun","Kraków"));
        when(mapper.mapToVetDto(any())).thenReturn(updatedVetDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedVetDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/vet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Smile and Fun")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", Matchers.is("Kraków")))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldDeleteVetTest() throws Exception {

        //GIVEN
        Vet vet = new Vet(1L,"Smile","Kraków");

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/vet/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}