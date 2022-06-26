package com.kodilla.cats.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {

    private Long id;
    private String name;
    private String age;
    private String description;

}
