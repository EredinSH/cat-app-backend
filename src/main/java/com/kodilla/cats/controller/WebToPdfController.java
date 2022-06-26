package com.kodilla.cats.controller;

import com.kodilla.cats.domain.WebToPdf;
import com.kodilla.cats.domain.WebToPdfDto;
import com.kodilla.cats.mapper.WebToPdfMapper;
import com.kodilla.cats.webpdf.client.WebToPdfClient;
import com.kodilla.cats.webpdf.service.WebToPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/pdf")
@CrossOrigin("*")
public class WebToPdfController {

    private final WebToPdfMapper mapper;
    private final WebToPdfService service;

    public WebToPdfController(WebToPdfMapper mapper, WebToPdfService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("pdf")
    public ResponseEntity<List<WebToPdfDto>> getWebToPdf(String name) {
        List<WebToPdf> pdfList = mapper.mapToWebToPdf(service.getWebToPdf(name));
        return ResponseEntity.ok(mapper.mapToWebToPdfDto(pdfList));
    }

}
