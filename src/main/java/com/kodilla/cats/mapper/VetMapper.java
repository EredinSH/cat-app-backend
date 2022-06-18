package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.Vet;
import com.kodilla.cats.domain.VetDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetMapper {

    public Vet mapToVet(final VetDto vetDto) {
        return new Vet(vetDto.getId(), vetDto.getName(), vetDto.getLocation());
    }

    public VetDto mapToVetDto(final Vet vet) {
        return new VetDto(vet.getId(), vet.getName(), vet.getLocation());
    }

    public List<VetDto> mapToVetDtoList(final List<Vet> vetList) {
        return vetList.stream()
                .map(this::mapToVetDto)
                .collect(Collectors.toList());
    }

}
