package com.trabajointegrador.demo.dto;

import com.trabajointegrador.demo.model.Periodicidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class EventoDto {
    private Long id;
    private String nombre;
    private String ubicacion;
    private Boolean activo;
    private LocalDate fechaAlta;
    private Periodicidad periodicidad;

}
