package com.kodilla.cats.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class Mail {

    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

}
