package com.kodilla.cats.google.service;

import com.kodilla.cats.domain.GoogleSearchDto;
import com.kodilla.cats.google.client.GoogleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleService {

    private final GoogleClient googleClient;

    public List<GoogleSearchDto> getGoogleSearch(String name) {
        return googleClient.getGoogleSearch(name);
    }

}
