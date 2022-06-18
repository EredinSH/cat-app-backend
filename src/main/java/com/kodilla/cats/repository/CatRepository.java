package com.kodilla.cats.repository;

import com.kodilla.cats.domain.Cat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends CrudRepository<Cat,Long> {

    @Override
    List<Cat> findAll();

    @Override
    Cat save(Cat cat);

    @Override
    Optional<Cat> findById(Long id);

    @Override
    void deleteById(Long id);
}
