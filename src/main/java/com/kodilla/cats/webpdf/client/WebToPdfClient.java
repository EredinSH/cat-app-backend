package com.kodilla.cats.webpdf.client;

import com.kodilla.cats.config.WebToPdfConfig;

import com.kodilla.cats.domain.WebToPdfDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WebToPdfClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebToPdfClient.class);

    private final RestTemplate restTemplate;
    private final WebToPdfConfig webToPdfConfig;

    public List<WebToPdfDto> getWebToPdf(String name) {
        URI url = getWebToPdfUri(name);

        try {
            WebToPdfDto[] result = restTemplate.getForObject(url,WebToPdfDto[].class);
            return Optional.ofNullable(result)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getRequestedUrl()) && Objects.nonNull(p.getPdfUrl()))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

    }

    private URI getWebToPdfUri(String name) {
        return UriComponentsBuilder.fromHttpUrl(webToPdfConfig.getWebpdfApiEndpoint())
                .queryParam("url",name)
                .queryParam("grayscale","true")
                .queryParam("pagesize","B4")
                .queryParam("apikey",webToPdfConfig.getWebpdfKey())
                .build().encode().toUri();
    }

}
