package com.kodilla.cats.controller;

import com.google.gson.Gson;
import com.kodilla.cats.domain.Information;
import com.kodilla.cats.domain.InformationDto;
import com.kodilla.cats.mapper.InformationMapper;
import com.kodilla.cats.service.DbInformationService;
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
@WebMvcTest(InformationController.class)
public class InformationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbInformationService service;

    @MockBean
    private InformationMapper mapper;


    @Test
    public void shouldGetInformationsTest() throws Exception {

        //GIVEN
        List<Information> informationList = List.of(new Information(1L,"Beauty","Wash your cat once a week"));

        List<InformationDto> informationDtoList = List.of(new InformationDto(1L,"Beauty","Wash your cat once a week"));

        when(service.getAllInformations()).thenReturn(informationList);
        when(mapper.mapToInformationDtoList(informationList)).thenReturn(informationDtoList);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/information")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category", is("Beauty")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", is("Wash your cat once a week")));

    }

    @Test
    public void shouldGetInformationTest() throws Exception {

        //GIVEN
        Information information = new Information(1L,"Beauty","Wash your cat once a week");
        InformationDto informationDto = new InformationDto(1L,"Beauty","Wash your cat once a week");

        when(service.getInformation(1L)).thenReturn(information);
        when(mapper.mapToInformationDto(information)).thenReturn(informationDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/information/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", is("Beauty")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", is("Wash your cat once a week")));

    }

    @Test
    public void addInformationTest() throws Exception {

        //GIVEN
        Information information = new Information(1L,"Beauty","Wash your cat once a week");
        InformationDto informationDto = new InformationDto(1L,"Beauty","Wash your cat once a week");

        when(mapper.mapToInformation(informationDto)).thenReturn(information);
        when(service.saveInformation(information)).thenReturn(new Information(1L,"Beauty","Wash your cat once a week"));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(informationDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/information")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateInformationTest() throws Exception {

        //GIVEN
        Information information = new Information(1L,"Beauty","Wash your cat once a week");
        InformationDto informationDto = new InformationDto(1L,"Beauty","Wash your cat once a week");
        InformationDto updatedInformationDto = new InformationDto(2L,"Beauty","Wash your cat twice a week");


        when(mapper.mapToInformation(informationDto)).thenReturn(information);
        when(service.saveInformation(information)).thenReturn(new Information(2L,"Beauty", "Wash your cat twice a week"));
        when(mapper.mapToInformationDto(any())).thenReturn(updatedInformationDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedInformationDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/information")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is("Beauty")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Wash your cat twice a week")))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldDeleteInformationTest() throws Exception {

        //GIVEN
        Information information = new Information(1L,"Beauty","Wash your cat once a week");

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/information/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
