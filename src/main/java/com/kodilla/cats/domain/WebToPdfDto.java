package com.kodilla.cats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebToPdfDto {

    @JsonProperty("requested_url")
    private String requestedUrl;

    @JsonProperty("pdf_url")
    private String pdfUrl;

}
