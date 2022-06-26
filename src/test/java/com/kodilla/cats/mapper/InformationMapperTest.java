package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.Information;
import com.kodilla.cats.domain.InformationDto;;
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
class InformationMapperTest {

    @Mock
    InformationMapper mapper;

    @Test
    public void mapToInformationTest() {

        //GIVEN
        InformationDto informationDto = new InformationDto(1L,"Beauty","Wash your cat once a week");
        when(mapper.mapToInformation(informationDto)).thenReturn(new Information(1L,"Beauty","Wash your cat once a week"));

        //WHEN
        Information information = mapper.mapToInformation(informationDto);

        //THEN
        assertThat(information).isNotNull();
        assertEquals(1l,information.getId());
        assertEquals("Beauty",information.getCategory());
        assertEquals("Wash your cat once a week",information.getContent());

    }

    @Test
    public void mapToInformationDtoTest() {

        //GIVEN
        Information information = new Information(1L,"Beauty","Wash your cat once a week");
        when(mapper.mapToInformationDto(information)).thenReturn(new InformationDto(1L,"Beauty","Wash your cat once a week"));

        //WHEN
        InformationDto informationDto = mapper.mapToInformationDto(information);

        //THEN
        assertThat(informationDto).isNotNull();
        assertEquals(1l,informationDto.getId());
        assertEquals("Beauty",informationDto.getCategory());
        assertEquals("Wash your cat once a week",informationDto.getContent());

    }

    @Test
    public void mapToInformationDtoListTest() {

        //GIVEN
        Information information1 = new Information(1L,"Beauty","Wash your cat once a week");
        Information information2 = new Information(2L,"Food","Feed your cat everyday");
        List<Information> informationList = new ArrayList<>();
        informationList.add(information1);
        informationList.add(information2);

        List<InformationDto> informationDtoList = List.of(new InformationDto(1L,"Beauty","Wash your cat once a week"),(new InformationDto(2L,"Food","Feed your cat everyday")));

        when(mapper.mapToInformationDtoList(informationList)).thenReturn(informationDtoList);

        //WHEN
        List<InformationDto> result = mapper.mapToInformationDtoList(informationList);

        //THEN
        assertEquals(2,result.size());
        assertEquals("Food",result.get(1).getCategory());

    }

}