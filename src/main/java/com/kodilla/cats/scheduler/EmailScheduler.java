package com.kodilla.cats.scheduler;

import com.kodilla.cats.config.AdminConfig;
import com.kodilla.cats.domain.Mail;
import com.kodilla.cats.repository.CatRepository;
import com.kodilla.cats.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Cats: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final CatRepository catRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {

        long size = catRepository.count();
        simpleEmailService.send(Mail.builder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .message("Currently in database you got: " + size + (size == 1 ? " cat" : " cats"))
                .toCc(null)
                .build());

    }

}
