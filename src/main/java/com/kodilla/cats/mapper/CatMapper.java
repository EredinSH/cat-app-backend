package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.domain.CatDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatMapper {

    public Cat mapToCat(final CatDto catDto) {
        return new Cat(catDto.getId(), catDto.getName(), catDto.getAge(), catDto.getDescription());
    }

    public CatDto mapToCatDto(final Cat cat) {
        return new CatDto(cat.getId(), cat.getName(), cat.getAge(), cat.getDescription());
    }

    public List<CatDto> mapToCatDtoList(final List<Cat> catList) {
        return catList.stream()
                .map(this::mapToCatDto)
                .collect(Collectors.toList());
    }

}
