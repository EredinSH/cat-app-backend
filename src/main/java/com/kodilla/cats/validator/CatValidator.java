package com.kodilla.cats.validator;

import com.kodilla.cats.domain.Cat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatValidator.class);

    public void validateCat(final Cat cat) {
        if(cat.getName().contains("test")) {
            LOGGER.info("Someone is testing my application");
        } else {
            LOGGER.info("Seems that my application is used in proper way.");
        }
    }

    public List<Cat> validateCatList(final List<Cat> catList) {
        LOGGER.info("Starting filtering lists...");
        List<Cat> filteredList = catList.stream()
                .filter(cat -> !cat.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Lists have been filtered. Current size: " + filteredList.size());

        return  filteredList;
    }
}
