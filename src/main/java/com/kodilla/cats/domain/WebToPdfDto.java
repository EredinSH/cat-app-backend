package com.kodilla.cats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebToPdfDto {

    @JsonProperty("requested_url")
    private String requested_url;

    @JsonProperty("pdf_url")
    private String pdf_url;

}
