package com.kodilla.cats.repository;

import com.kodilla.cats.domain.Information;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformationRepository extends CrudRepository<Information,Long> {

    @Override
    List<Information> findAll();

    @Override
    Information save(Information information);

    @Override
    Optional<Information> findById(Long id);

    @Override
    void deleteById(Long id);

}
