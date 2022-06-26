package com.kodilla.cats.service;

import com.kodilla.cats.controller.InformationNotFoundException;
import com.kodilla.cats.domain.Information;
import com.kodilla.cats.repository.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DbInformationService {

    @Autowired
    private final InformationRepository repository;

    public List<Information> getAllInformations() {
        return repository.findAll();
    }

    public Information getInformation(final Long informationId) throws InformationNotFoundException {
        return repository.findById(informationId).orElseThrow(InformationNotFoundException::new);
    }

    public Information saveInformation(final Information information) {
        return repository.save(information);
    }

    public void deleteById(final Long informationId) throws InformationNotFoundException {
        repository.deleteById(informationId);
    }

}
