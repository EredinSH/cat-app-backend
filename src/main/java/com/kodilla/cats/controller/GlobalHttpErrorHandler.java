package com.kodilla.cats.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CatNotFoundException.class)
    public ResponseEntity<Object> handleCatNotFoundException(CatNotFoundException exception) {
        return new ResponseEntity<>("Cat does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InformationNotFoundException.class)
    public ResponseEntity<Object> handleInformationNotFoundException(InformationNotFoundException exception) {
        return new ResponseEntity<>("Information does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VetNotFoundException.class)
    public ResponseEntity<Object> handleVetNotFoundException(VetNotFoundException exception) {
        return new ResponseEntity<>("Vet does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VolunteerNotFoundException.class)
    public ResponseEntity<Object> handleVolunteerNotFoundException(VolunteerNotFoundException exception) {
        return new ResponseEntity<>("Volunteer does not exist", HttpStatus.BAD_REQUEST);
    }

}
