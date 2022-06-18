package com.kodilla.cats.repository;

import com.kodilla.cats.domain.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VetRepository extends CrudRepository<Vet,Long>  {

    @Override
    List<Vet> findAll();

    @Override
    Vet save(Vet vet);

    @Override
    Optional<Vet> findById(Long id);

}
