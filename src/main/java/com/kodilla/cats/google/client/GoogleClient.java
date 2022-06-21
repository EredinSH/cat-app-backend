package com.kodilla.cats.google.client;

import com.kodilla.cats.domain.GoogleSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleClient {

    @Value("${google.api.endpoint}")
    private String googleApiEndpoint;
    @Value("${google.app.key}")
    private String googleKey;

    private final RestTemplate restTemplate;

    public List<GoogleSearchDto> getGoogleSearch(String request) {
        GoogleSearchDto[] searchResponse = restTemplate.getForObject("https://api.apilayer.com/google_search?q=cat", GoogleSearchDto[].class);
    }



}
