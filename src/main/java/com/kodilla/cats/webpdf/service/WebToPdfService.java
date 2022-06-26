package com.kodilla.cats.webpdf.service;

import com.kodilla.cats.domain.WebToPdfDto;
import com.kodilla.cats.webpdf.client.WebToPdfClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebToPdfService {

    private final WebToPdfClient webToPdfClient;

    public List<WebToPdfDto> getWebToPdf(String name) {
        return webToPdfClient.getWebToPdf(name);
    }

}
