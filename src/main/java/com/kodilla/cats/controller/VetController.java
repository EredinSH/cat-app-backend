package com.kodilla.cats.controller;

import com.kodilla.cats.domain.Vet;
import com.kodilla.cats.domain.VetDto;
import com.kodilla.cats.mapper.VetMapper;
import com.kodilla.cats.service.DbVetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/vet")
@CrossOrigin("*")
public class VetController {

    private final DbVetService service;
    private final VetMapper mapper;

    public VetController(DbVetService service, VetMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<VetDto>> getVets() {
        List<Vet> vets = service.getAllVets();
        return ResponseEntity.ok(mapper.mapToVetDtoList(vets));
    }

    @GetMapping(value = "{vetId}")
    public ResponseEntity<VetDto> getVet(@PathVariable Long vetId) throws VetNotFoundException {
        return ResponseEntity.ok(mapper.mapToVetDto(service.getVet(vetId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addVet(@RequestBody VetDto vetDto) {
        Vet vet = mapper.mapToVet(vetDto);
        service.saveVet(vet);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<VetDto> updateVet(@RequestBody VetDto vetDto) {
        Vet vet = mapper.mapToVet(vetDto);
        Vet savedVet = service.saveVet(vet);
        return ResponseEntity.ok(mapper.mapToVetDto(savedVet));
    }

    @DeleteMapping(value = "{vetId}")
    public ResponseEntity<Void> deleteVet(Long vetId) throws VetNotFoundException {
        service.deleteById(vetId);
        return ResponseEntity.ok().build();
    }

}
