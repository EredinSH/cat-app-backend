package com.kodilla.cats.repository;

import com.kodilla.cats.domain.Volunteer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer,Long>  {

    @Override
    List<Volunteer> findAll();

    @Override
    Volunteer save(Volunteer volunteer);

    @Override
    Optional<Volunteer> findById(Long id);

    @Override
    void deleteById(Long id);

}
