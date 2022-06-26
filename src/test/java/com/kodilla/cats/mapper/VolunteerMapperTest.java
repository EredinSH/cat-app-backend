package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.Volunteer;
import com.kodilla.cats.domain.VolunteerDto;
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
class VolunteerMapperTest {

    @Mock
    VolunteerMapper mapper;

    @Test
    public void mapToVolunteerTest() {

        //GIVEN
        VolunteerDto volunteerDto = new VolunteerDto(1L,"Nanami Kento","28","Boring");
        when(mapper.mapToVolunteer(volunteerDto)).thenReturn(new Volunteer(1L,"Nanami Kento","28","Boring"));

        //WHEN
        Volunteer volunteer = mapper.mapToVolunteer(volunteerDto);

        //THEN
        assertThat(volunteer).isNotNull();
        assertEquals(1l,volunteer.getId());
        assertEquals("Nanami Kento",volunteer.getName());
        assertEquals("28",volunteer.getAge());
        assertEquals("Boring",volunteer.getDescription());

    }

    @Test
    public void mapToVolunteerDtoTest() {

        //GIVEN
        Volunteer volunteer = new Volunteer(1L,"Nanami Kento","28","Boring");
        when(mapper.mapToVolunteerDto(volunteer)).thenReturn(new VolunteerDto(1L,"Nanami Kento","28","Boring"));

        //WHEN
        VolunteerDto volunteerDto = mapper.mapToVolunteerDto(volunteer);

        //THEN
        assertThat(volunteerDto).isNotNull();
        assertEquals(1l,volunteerDto.getId());
        assertEquals("Nanami Kento",volunteerDto.getName());
        assertEquals("28",volunteerDto.getAge());
        assertEquals("Boring",volunteerDto.getDescription());

    }

    @Test
    public void mapToVolunteerDtoListTest() {

        //GIVEN
        Volunteer volunteer1 = new Volunteer(1L,"Nanami Kento","28","Boring");
        Volunteer volunteer2 = new Volunteer(2L,"Suguru Geto","27","Fun");
        List<Volunteer> volunteersList = new ArrayList<>();
        volunteersList.add(volunteer1);
        volunteersList.add(volunteer2);

        List<VolunteerDto> volunteerDtoList = List.of(new VolunteerDto(1L,"Nanami Kento","28","Boring"),(new VolunteerDto(2L,"Suguru Geto","27","Fun")));

        when(mapper.mapToVolunteerDtoList(volunteersList)).thenReturn(volunteerDtoList);

        //WHEN
        List<VolunteerDto> result = mapper.mapToVolunteerDtoList(volunteersList);

        //THEN
        assertEquals(2,result.size());
        assertEquals("Suguru Geto",result.get(1).getName());

    }

}