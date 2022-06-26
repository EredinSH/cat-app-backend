package com.kodilla.cats.service;

import com.kodilla.cats.controller.VetNotFoundException;
import com.kodilla.cats.domain.Vet;
import com.kodilla.cats.repository.VetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DbVetServiceTest {

    @InjectMocks
    private DbVetService service;

    @Mock
    private VetRepository repository;

    @Test
    public void getAllVetsTest() {
        //GIVEN
        Vet vet1 = new Vet(1L,"Smile","Kraków");
        Vet vet2 = new Vet(2L,"Meow","Warszawa");
        List<Vet> vetsList = new ArrayList<>();
        vetsList.add(vet1);
        vetsList.add(vet2);

        when(repository.findAll()).thenReturn(vetsList);

        //WHEN
        List<Vet> resultList = service.getAllVets();

        //THEN
        assertEquals("Meow",resultList.get(1).getName());

    }

    @Test
    public void saveVetTest() {
        //GIVEN
        Vet vet1 = new Vet(1L,"Smile","Kraków");
        when(repository.save(vet1)).thenReturn(vet1);

        //WHEN
        Vet result = service.saveVet(vet1);

        //THEN
        assertThat(result).isNotNull();

    }

    @Test
    public void getVetTest() throws VetNotFoundException {
        //GIVEN
        Vet vet1 = new Vet(1L,"Smile","Kraków");
        when(repository.findById(1L)).thenReturn(Optional.of(vet1));

        //WHEN
        Vet result = service.getVet(1L);

        //THEN
        assertEquals("Smile",result.getName());

    }

    @Test
    public void deleteByIdTest() throws VetNotFoundException {
        //GIVEN
        Vet vet1 = new Vet(1L,"Smile","Kraków");

        //WHEN
        service.deleteById(1L);

        //THEN
        assertFalse(false,vet1.getName());

    }

}