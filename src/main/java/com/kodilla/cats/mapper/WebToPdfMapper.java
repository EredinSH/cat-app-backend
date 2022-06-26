package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.GoogleSearch;
import com.kodilla.cats.domain.GoogleSearchDto;
import com.kodilla.cats.domain.WebToPdf;
import com.kodilla.cats.domain.WebToPdfDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class WebToPdfMapper {

    public List<WebToPdfDto> mapToWebToPdfDto(final List<WebToPdf> webToPdfs) {
        return webToPdfs.stream()
                .map(pdf -> new WebToPdfDto(pdf.getRequestedUrl(), pdf.getPdfUrl()))
                .collect(toList());
    }

    public List<WebToPdf> mapToWebToPdf(final List<WebToPdfDto> webToPdfDto) {
        return webToPdfDto.stream()
                .map(pdf ->
                        new WebToPdf(pdf.getRequestedUrl(),pdf.getPdfUrl()))
                .collect(toList());
    }

}
