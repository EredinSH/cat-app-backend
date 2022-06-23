package com.kodilla.cats.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class WebToPdfConfig {

    @Value("${webpdf.api.endpoint}")
    private String webpdfApiEndpoint;
    @Value("${webpdf.app.key}")
    private String webpdfKey;

}
