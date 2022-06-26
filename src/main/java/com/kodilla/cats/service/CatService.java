package com.kodilla.cats.service;

import com.kodilla.cats.config.AdminConfig;
import com.kodilla.cats.controller.CatController;
import com.kodilla.cats.domain.CatDto;
import com.kodilla.cats.domain.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class CatService {

    private static final String SUBJECT = "Cats: New Cat";
    private final CatController catController;
    private final SimpleEmailService emailService;
    private final AdminConfig adminConfig;

    public ResponseEntity<Void> cat(final CatDto catDto) {
        ResponseEntity<Void> newCat = catController.addCat(catDto);

        ofNullable(newCat).ifPresent(cat -> emailService.send((Mail.builder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .message("New cat: " + catDto.getName() + " has been created on your site")
                .toCc(null)
                .build())));

        return newCat;
    }

}
