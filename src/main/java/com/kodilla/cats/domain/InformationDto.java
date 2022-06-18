package com.kodilla.cats.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InformationDto {

    private Long id;
    private String category;
    private String content;

}
