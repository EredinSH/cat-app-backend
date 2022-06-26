package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.Vet;
import com.kodilla.cats.domain.VetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetMapperTest {

    @Mock
    VetMapper mapper;

    @Test
    public void mapToVetTest() {

        //GIVEN
        VetDto vetDto = new VetDto(1L,"Smile","Kraków");
        when(mapper.mapToVet(vetDto)).thenReturn(new Vet(1L,"Smile","Kraków"));

        //WHEN
        Vet vet = mapper.mapToVet(vetDto);

        //THEN
        assertThat(vet).isNotNull();
        assertEquals(1l,vet.getId());
        assertEquals("Smile",vet.getName());
        assertEquals("Kraków",vet.getLocation());

    }

    @Test
    public void mapToVetDtoTest() {

        //GIVEN
        Vet vet = new Vet(1L,"Smile","Kraków");
        when(mapper.mapToVetDto(vet)).thenReturn(new VetDto(1L,"Smile","Kraków"));

        //WHEN
        VetDto vetDto = mapper.mapToVetDto(vet);

        //THEN
        assertThat(vetDto).isNotNull();
        assertEquals(1l,vetDto.getId());
        assertEquals("Smile",vetDto.getName());
        assertEquals("Kraków",vetDto.getLocation());

    }

    @Test
    public void mapToVetDtoListTest() {

        //GIVEN
        Vet vet1 = new Vet(1L,"Smile","Kraków");
        Vet vet2 = new Vet(2L,"Meow","Warszawa");
        List<Vet> vetsList = new ArrayList<>();
        vetsList.add(vet1);
        vetsList.add(vet2);

        List<VetDto> vetDtoList = List.of(new VetDto(1L,"Smile","Kraków"),(new VetDto(2L,"Meow","Warszawa")));

        when(mapper.mapToVetDtoList(vetsList)).thenReturn(vetDtoList);

        //WHEN
        List<VetDto> result = mapper.mapToVetDtoList(vetsList);

        //THEN
        assertEquals(2,result.size());
        assertEquals("Meow",result.get(1).getName());

    }

}