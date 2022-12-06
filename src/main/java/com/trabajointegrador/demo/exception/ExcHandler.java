package com.trabajointegrador.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageCustom> notFoundException(NotFoundException exception){
        return ResponseEntity.status(404).body(new MessageCustom(exception.getMessage(), "404"));
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageCustom> badRequestException(BadRequestException exception){
        return ResponseEntity.status(400).body(new MessageCustom(exception.getMessage(), "400"));
    }
}

