package com.kodilla.cats.controller;

import com.kodilla.cats.domain.Information;
import com.kodilla.cats.domain.InformationDto;
import com.kodilla.cats.mapper.InformationMapper;
import com.kodilla.cats.service.DbInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/information")
@CrossOrigin("*")
public class InformationController {

    private final DbInformationService service;
    private final InformationMapper mapper;

    public InformationController(DbInformationService service, InformationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<InformationDto>> getInfomations() {
        List<Information> informations = service.getAllInformations();
        return ResponseEntity.ok(mapper.mapToInformationDtoList(informations));
    }

    @GetMapping(value = "{informationId}")
    public ResponseEntity<InformationDto> getInformation(@PathVariable Long informationId) throws InformationNotFoundException {
        return ResponseEntity.ok(mapper.mapToInformationDto(service.getInformation(informationId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addInformation(@RequestBody InformationDto informationDto) {
        Information information = mapper.mapToInformation(informationDto);
        service.saveInformation(information);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<InformationDto> updateInformation(@RequestBody InformationDto informationDto) {
        Information information = mapper.mapToInformation(informationDto);
        Information savedInformation = service.saveInformation(information);
        return ResponseEntity.ok(mapper.mapToInformationDto(savedInformation));
    }

    @DeleteMapping(value = "{informationId}")
    public ResponseEntity<Void> deleteInformation(Long informationId) throws InformationNotFoundException {
        service.deleteById(informationId);
        return ResponseEntity.ok().build();
    }

}
