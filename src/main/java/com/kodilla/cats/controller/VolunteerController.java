package com.kodilla.cats.controller;


import com.kodilla.cats.domain.Volunteer;
import com.kodilla.cats.domain.VolunteerDto;
import com.kodilla.cats.mapper.VolunteerMapper;
import com.kodilla.cats.service.DbVolunteerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/volunteer")
@CrossOrigin("*")
public class VolunteerController {

    private final DbVolunteerService service;
    private final VolunteerMapper mapper;

    public VolunteerController(DbVolunteerService service, VolunteerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<VolunteerDto>> getVolunteers() {
        List<Volunteer> vets = service.getAllVolunteers();
        return ResponseEntity.ok(mapper.mapToVolunteerDtoList(vets));
    }

    @GetMapping(value = "{volunteerId}")
    public ResponseEntity<VolunteerDto> getVolunteer(@PathVariable Long volunteerId) throws VolunteerNotFoundException {
        return ResponseEntity.ok(mapper.mapToVolunteerDto(service.getVolunteer(volunteerId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addVolunteer(@RequestBody VolunteerDto volunteerDto) {
        Volunteer volunteer = mapper.mapToVolunteer(volunteerDto);
        service.saveVolunteer(volunteer);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<VolunteerDto> updateVolunteer(@RequestBody VolunteerDto volunteerDto) {
        Volunteer volunteer = mapper.mapToVolunteer(volunteerDto);
        Volunteer savedVolunteer = service.saveVolunteer(volunteer);
        return ResponseEntity.ok(mapper.mapToVolunteerDto(savedVolunteer));
    }

    @DeleteMapping(value = "{volunteerId}")
    public ResponseEntity<Void> deleteVolunteer(Long volunteerId) throws VolunteerNotFoundException {
        service.deleteById(volunteerId);
        return ResponseEntity.ok().build();
    }

}
