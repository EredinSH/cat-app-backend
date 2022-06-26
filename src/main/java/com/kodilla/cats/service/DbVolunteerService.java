package com.kodilla.cats.service;

import com.kodilla.cats.controller.VolunteerNotFoundException;
import com.kodilla.cats.domain.Volunteer;
import com.kodilla.cats.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbVolunteerService {

    @Autowired
    private final VolunteerRepository repository;

    public List<Volunteer> getAllVolunteers() {
        return repository.findAll();
    }

    public Volunteer getVolunteer(final Long volunteerId) throws VolunteerNotFoundException {
        return repository.findById(volunteerId).orElseThrow(VolunteerNotFoundException::new);
    }

    public Volunteer saveVolunteer(final Volunteer volunteer) {
        return repository.save(volunteer);
    }

    public void deleteById(final Long volunteerId) throws VolunteerNotFoundException {
        repository.deleteById(volunteerId);
    }
}
