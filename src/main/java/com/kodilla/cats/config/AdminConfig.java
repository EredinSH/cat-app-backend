package com.kodilla.cats.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AdminConfig {

    @Value("${spring.mail.username}")
    private String adminMail;

}
