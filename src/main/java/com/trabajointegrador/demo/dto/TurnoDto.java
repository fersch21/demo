package com.trabajointegrador.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDto {

    private Long id;
    private LocalDate fechaTurno;
    private LocalDateTime horaTurno;
}
