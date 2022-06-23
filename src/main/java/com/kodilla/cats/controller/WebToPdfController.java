package com.kodilla.cats.controller;

import com.kodilla.cats.domain.WebToPdfDto;
import com.kodilla.cats.webpdf.client.WebToPdfClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/pdf")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WebToPdfController {

    private final WebToPdfClient webToPdfClient;

    @GetMapping("pdf")
    public ResponseEntity<List<WebToPdfDto>> getWebToPdf(String name) {
        return ResponseEntity.ok(webToPdfClient.getWebToPdf(name));
    }

}
