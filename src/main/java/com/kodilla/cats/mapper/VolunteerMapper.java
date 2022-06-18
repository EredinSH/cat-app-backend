package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.Volunteer;
import com.kodilla.cats.domain.VolunteerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerMapper {

    public Volunteer mapToVolunteer(final VolunteerDto volunteerDto) {
        return new Volunteer(volunteerDto.getId(), volunteerDto.getName(), volunteerDto.getAge(), volunteerDto.getDescription());
    }

    public VolunteerDto mapToVolunteerDto(final Volunteer volunteer) {
        return new VolunteerDto(volunteer.getId(), volunteer.getName(), volunteer.getAge(), volunteer.getDescription());
    }

    public List<VolunteerDto> mapToVolunteerDtoList(final List<Volunteer> volunteerList) {
        return volunteerList.stream()
                .map(this::mapToVolunteerDto)
                .collect(Collectors.toList());
    }

}
