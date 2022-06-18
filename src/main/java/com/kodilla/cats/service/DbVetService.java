package com.kodilla.cats.service;

import com.kodilla.cats.controller.VetNotFoundException;
import com.kodilla.cats.domain.Vet;
import com.kodilla.cats.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DbVetService {

    @Autowired
    private final VetRepository vetRepository;

    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }

    public Vet getVet(final Long vetId) throws VetNotFoundException {
        return vetRepository.findById(vetId).orElseThrow(VetNotFoundException::new);
    }

    public Vet saveVet(final Vet vet) {
        return vetRepository.save(vet);
    }

}
