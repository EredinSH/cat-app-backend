package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.Information;
import com.kodilla.cats.domain.InformationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformationMapper {

    public Information mapToInformation(final InformationDto informationDto) {
        return new Information(informationDto.getId(), informationDto.getCategory(), informationDto.getContent());
    }

    public InformationDto mapToInformationDto(final Information information) {
        return new InformationDto(information.getId(), information.getCategory(), information.getContent());
    }

    public List<InformationDto> mapToInformationDtoList(final List<Information> informationList) {
        return informationList.stream()
                .map(this::mapToInformationDto)
                .collect(Collectors.toList());
    }

}
