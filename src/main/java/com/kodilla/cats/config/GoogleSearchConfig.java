package com.kodilla.cats.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class GoogleSearchConfig {

    @Value("${google.api.endpoint}")
    private String googleApiEndpoint;
    @Value("${google.app.key}")
    private String googleKey;

}
