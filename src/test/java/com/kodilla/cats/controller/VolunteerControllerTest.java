package com.kodilla.cats.controller;

import com.google.gson.Gson;
import com.kodilla.cats.domain.Volunteer;
import com.kodilla.cats.domain.VolunteerDto;
import com.kodilla.cats.mapper.VolunteerMapper;
import com.kodilla.cats.service.DbVolunteerService;
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
@WebMvcTest(VolunteerController.class)
class VolunteerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbVolunteerService service;

    @MockBean
    private VolunteerMapper mapper;


    @Test
    public void shouldGetVolunteersTest() throws Exception {

        //GIVEN
        List<Volunteer> volunteerList = List.of(new Volunteer(1L,"Nanami Kento","28","Boring"));

        List<VolunteerDto> volunteerDtoList = List.of(new VolunteerDto(1L,"Nanami Kento","28","Boring"));

        when(service.getAllVolunteers()).thenReturn(volunteerList);
        when(mapper.mapToVolunteerDtoList(volunteerList)).thenReturn(volunteerDtoList);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/volunteer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Nanami Kento")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age", is("28")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", is("Boring")));

    }

    @Test
    public void shouldGetVolunteerTest() throws Exception {

        //GIVEN
        Volunteer volunteer = new Volunteer(1L,"Nanami Kento","28","Boring");
        VolunteerDto volunteerDto = new VolunteerDto(1L,"Nanami Kento","28","Boring");

        when(service.getVolunteer(1L)).thenReturn(volunteer);
        when(mapper.mapToVolunteerDto(volunteer)).thenReturn(volunteerDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/volunteer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Nanami Kento")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", is("28")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("Boring")));

    }

    @Test
    public void addVolunteerTest() throws Exception {

        //GIVEN
        Volunteer volunteer = new Volunteer(1L,"Nanami Kento","28","Boring");
        VolunteerDto volunteerDto = new VolunteerDto(1L,"Nanami Kento","28","Boring");

        when(mapper.mapToVolunteer(volunteerDto)).thenReturn(volunteer);
        when(service.saveVolunteer(volunteer)).thenReturn(new Volunteer(1L,"Nanami Kento","28","Boring"));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(volunteerDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/volunteer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateVolunteerTest() throws Exception {

        //GIVEN
        Volunteer volunteer = new Volunteer(1L,"Nanami Kento","28","Boring");
        VolunteerDto volunteerDto = new VolunteerDto(1L,"Nanami Kento","28","Boring");
        VolunteerDto updatedVolunteerDto = new VolunteerDto(2L,"Nanami Kento","29","Boring");



        when(mapper.mapToVolunteer(volunteerDto)).thenReturn(volunteer);
        when(service.saveVolunteer(volunteer)).thenReturn(new Volunteer(2L,"Nanami Kento","29","Boring"));
        when(mapper.mapToVolunteerDto(any())).thenReturn(updatedVolunteerDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedVolunteerDto);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/volunteer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Nanami Kento")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is("29")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Boring")))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldDeleteVeolunteerTest() throws Exception {

        //GIVEN
        Volunteer volunteer = new Volunteer(1L,"Nanami Kento","28","Boring");

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/volunteer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}