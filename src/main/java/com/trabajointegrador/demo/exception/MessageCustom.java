package com.trabajointegrador.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class MessageCustom {
    private String message;
    private String status_code;
}
