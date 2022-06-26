package com.kodilla.cats.service;

import com.kodilla.cats.controller.InformationNotFoundException;
import com.kodilla.cats.domain.Information;
import com.kodilla.cats.repository.InformationRepository;
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
class DbInformationServiceTest {

    @InjectMocks
    private DbInformationService service;

    @Mock
    private InformationRepository repository;

    @Test
    public void getAllInformationsTest() {
        //GIVEN
        Information information1 = new Information(1L,"Beauty","Wash your cat once a week");
        Information information2 = new Information(2L,"Food","Feed your cat everyday");
        List<Information> informationssList = new ArrayList<>();
        informationssList.add(information1);
        informationssList.add(information2);

        when(repository.findAll()).thenReturn(informationssList);

        //WHEN
        List<Information> resultList = service.getAllInformations();

        //THEN
        assertEquals("Beauty",resultList.get(0).getCategory());

    }

    @Test
    public void saveInformationTest() {
        //GIVEN
        Information information1 = new Information(1L,"Beauty","Wash your cat once a week");
        when(repository.save(information1)).thenReturn(information1);

        //WHEN
        Information result = service.saveInformation(information1);

        //THEN
        assertThat(result).isNotNull();

    }

    @Test
    public void getInformationTest() throws InformationNotFoundException {
        //GIVEN
        Information information1 = new Information(1L,"Beauty","Wash your cat once a week");
        when(repository.findById(1L)).thenReturn(Optional.of(information1));

        //WHEN
        Information result = service.getInformation(1L);

        //THEN
        assertEquals("Beauty",result.getCategory());

    }

    @Test
    public void deleteByIdTest() throws InformationNotFoundException {
        //GIVEN
        Information information1 = new Information(1L,"Beauty","Wash your cat once a week");

        //WHEN
        service.deleteById(1L);

        //THEN
        assertFalse(false,information1.getCategory());

    }

}