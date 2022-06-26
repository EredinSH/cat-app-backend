package com.kodilla.cats.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class WebToPdf {

    private String requestedUrl;
    private String pdfUrl;

}
