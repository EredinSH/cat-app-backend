package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.GoogleSearch;
import com.kodilla.cats.domain.GoogleSearchDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GoogleMapperTest {

    @Mock
    GoogleMapper mapper;

    @Test
    public void mapToGoogleSearchDtoTest() {

        //GIVEN
        GoogleSearch googleSearch1 = new GoogleSearch("test1");
        GoogleSearch googleSearch2 = new GoogleSearch("test2");
        List<GoogleSearch> linkList = new ArrayList<>();
        linkList.add(googleSearch1);
        linkList.add(googleSearch2);

        List<GoogleSearchDto> linkDtoList = List.of(new GoogleSearchDto("test1"),(new GoogleSearchDto("test2")));

        when(mapper.mapToGoogleSearchDto(linkList)).thenReturn(linkDtoList);

        //WHEN
        List<GoogleSearchDto> result = mapper.mapToGoogleSearchDto(linkList);

        //THEN
        assertEquals(2,result.size());
        assertEquals("test2",result.get(1).getLink());

    }

    @Test
    public void mapToGoogleSearchTest() {

        //GIVEN
        GoogleSearchDto googleSearch1 = new GoogleSearchDto("test1");
        GoogleSearchDto googleSearch2 = new GoogleSearchDto("test2");
        List<GoogleSearchDto> linkDtoList = new ArrayList<>();
        linkDtoList.add(googleSearch1);
        linkDtoList.add(googleSearch2);

        List<GoogleSearch> linkList = List.of(new GoogleSearch("test1"),(new GoogleSearch("test2")));

        when(mapper.mapToGoogleSearch(linkDtoList)).thenReturn(linkList);

        //WHEN
        List<GoogleSearch> result = mapper.mapToGoogleSearch(linkDtoList);

        //THEN
        assertEquals(2,result.size());
        assertEquals("test2",result.get(1).getLink());

    }

}