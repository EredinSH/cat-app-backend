package com.kodilla.cats.controller;

import com.kodilla.cats.domain.GoogleSearchDto;
import com.kodilla.cats.google.client.GoogleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/google")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GoogleController {

    private final GoogleClient googleClient;

    @GetMapping("search")
    public void getGoogleSearch(String request) {
        List<GoogleSearchDto> result = googleClient.getGoogleSearch(request);
        result.forEach(googleSearchDto -> System.out.println(googleSearchDto.getQ()));
    }

}
