package com.kodilla.cats.controller;

import com.kodilla.cats.domain.GoogleSearch;
import com.kodilla.cats.domain.GoogleSearchDto;
import com.kodilla.cats.google.client.GoogleClient;
import com.kodilla.cats.google.service.GoogleService;
import com.kodilla.cats.mapper.GoogleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/google")
@CrossOrigin("*")
public class GoogleController {
    private final GoogleMapper mapper;
    private final GoogleService service;

    public GoogleController(GoogleMapper mapper, GoogleService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("search")
    public ResponseEntity<List<GoogleSearchDto>> getGoogleSearch(String name) {
        List<GoogleSearch> googleSearchList = mapper.mapToGoogleSearch(service.getGoogleSearch(name));
        return ResponseEntity.ok(mapper.mapToGoogleSearchDto(googleSearchList));
    }

}
