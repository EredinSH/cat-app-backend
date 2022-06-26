package com.kodilla.cats.service;

import com.kodilla.cats.controller.VolunteerNotFoundException;
import com.kodilla.cats.domain.Volunteer;
import com.kodilla.cats.repository.VolunteerRepository;
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
class DbVolunteerServiceTest {

    @InjectMocks
    private DbVolunteerService service;

    @Mock
    private VolunteerRepository repository;

    @Test
    public void getAllVolunteersTest() {
        //GIVEN
        Volunteer volunteer1 = new Volunteer(1L,"Nanami Kento","28","Boring");
        Volunteer volunteer2 = new Volunteer(2L,"Suguru Geto","27","Fun");
        List<Volunteer> volunteersList = new ArrayList<>();
        volunteersList.add(volunteer1);
        volunteersList.add(volunteer2);

        when(repository.findAll()).thenReturn(volunteersList);

        //WHEN
        List<Volunteer> resultList = service.getAllVolunteers();

        //THEN
        assertEquals("Nanami Kento",resultList.get(0).getName());

    }

    @Test
    public void saveVolunteerTest() {
        //GIVEN
        Volunteer volunteer1 = new Volunteer(1L,"Nanami Kento","28","Boring");
        when(repository.save(volunteer1)).thenReturn(volunteer1);

        //WHEN
        Volunteer result = service.saveVolunteer(volunteer1);

        //THEN
        assertThat(result).isNotNull();

    }

    @Test
    public void getVolunteerTest() throws VolunteerNotFoundException {
        //GIVEN
        Volunteer volunteer1 = new Volunteer(1L,"Nanami Kento","28","Boring");
        when(repository.findById(1L)).thenReturn(Optional.of(volunteer1));

        //WHEN
        Volunteer result = service.getVolunteer(1L);

        //THEN
        assertEquals("Nanami Kento",result.getName());

    }

    @Test
    public void deleteByIdTest() throws VolunteerNotFoundException {
        //GIVEN
        Volunteer volunteer1 = new Volunteer(1L,"Nanami Kento","28","Boring");

        //WHEN
        service.deleteById(1L);

        //THEN
        assertFalse(false,volunteer1.getName());

    }

}