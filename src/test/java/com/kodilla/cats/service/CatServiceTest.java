package com.kodilla.cats.service;

import com.kodilla.cats.config.AdminConfig;
import com.kodilla.cats.controller.CatController;
import com.kodilla.cats.domain.CatDto;
import com.kodilla.cats.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {

    @InjectMocks
    private CatService service;

    @Mock
    private CatController catController;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void catTest() {
        //GIVEN
        CatDto catDto1 = new CatDto(1L, "Puszek","4","test");

        Mail mail = Mail.builder()
                .mailTo("testTo")
                .subject("testSubject")
                .message("testMessage")
                .toCc("testCc")
                .build();

        catController.addCat(catDto1);
        emailService.send(mail);
        Long id = 1L;
        String name = "Puszek";
        String age = "4";
        String description = "test";

        //WHEN && THEN
        service.cat(catDto1);
        assertEquals(id,catDto1.getId());
        assertEquals(name,catDto1.getName());
        assertEquals(age,catDto1.getAge());
        assertEquals(description,catDto1.getDescription());

    }

}