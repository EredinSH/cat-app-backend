package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.WebToPdf;
import com.kodilla.cats.domain.WebToPdfDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
;import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WebToPdfMapperTest {

    @Mock
    WebToPdfMapper mapper;

    @Test
    public void mapToWebToPdfDtoTest() {

        //GIVEN
        WebToPdf web1 = new WebToPdf("test1","pdf1");
        WebToPdf web2 = new WebToPdf("test2","pdf2");
        List<WebToPdf> webList = new ArrayList<>();
        webList.add(web1);
        webList.add(web2);

        List<WebToPdfDto> webDtoList = List.of(new WebToPdfDto("test1","pdf1"),(new WebToPdfDto("test2","pdf2")));

        when(mapper.mapToWebToPdfDto(webList)).thenReturn(webDtoList);

        //WHEN
        List<WebToPdfDto> result = mapper.mapToWebToPdfDto(webList);

        //THEN
        assertEquals(2,result.size());
        assertEquals("test2",result.get(1).getRequestedUrl());
        assertEquals("pdf2",result.get(1).getPdfUrl());

    }

    @Test
    public void mapToWebToPdfTest() {

        //GIVEN
        WebToPdfDto web1 = new WebToPdfDto("test1","pdf1");
        WebToPdfDto web2 = new WebToPdfDto("test2","pdf2");
        List<WebToPdfDto> webDtoList = new ArrayList<>();
        webDtoList.add(web1);
        webDtoList.add(web2);

        List<WebToPdf> webList = List.of(new WebToPdf("test1","pdf1"),(new WebToPdf("test2","pdf2")));

        when(mapper.mapToWebToPdf(webDtoList)).thenReturn(webList);

        //WHEN
        List<WebToPdf> result = mapper.mapToWebToPdf(webDtoList);

        //THEN
        assertEquals(2,result.size());
        assertEquals("test2",result.get(1).getRequestedUrl());
        assertEquals("pdf2",result.get(1).getPdfUrl());

    }

}