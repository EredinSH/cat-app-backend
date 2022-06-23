package com.kodilla.cats.google.client;

import com.kodilla.cats.config.GoogleSearchConfig;
import com.kodilla.cats.domain.GoogleSearchDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoogleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleClient.class);

    private final RestTemplate restTemplate;
    private final GoogleSearchConfig googleSearchConfig;

    public List<GoogleSearchDto> getGoogleSearch(String name) {
        URI url = getGoogleSearchUri(name);

        try {
            GoogleSearchDto[] searchResult = restTemplate.getForObject(url,GoogleSearchDto[].class);
            return Optional.ofNullable(searchResult)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getLink()))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private URI getGoogleSearchUri(String name) {
        return UriComponentsBuilder.fromHttpUrl(googleSearchConfig.getGoogleApiEndpoint())
                .queryParam("q",name)
                .queryParam("apikey",googleSearchConfig.getGoogleKey())
                .build().encode().toUri();
    }

}
