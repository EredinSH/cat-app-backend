package com.kodilla.cats.service;

import com.kodilla.cats.controller.CatNotFoundException;
import com.kodilla.cats.domain.Cat;
import com.kodilla.cats.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbCatService {

    @Autowired
    private final CatRepository catRepository;

    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    public Cat getCat(final Long catId) throws CatNotFoundException {
        return catRepository.findById(catId).orElseThrow(CatNotFoundException::new);
    }

    public Cat saveCat(final Cat cat) {
        return catRepository.save(cat);
    }

    public void deleteById(final Long taskId) throws CatNotFoundException {
        catRepository.deleteById(taskId);
    }
}
