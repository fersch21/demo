package com.trabajointegrador.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
public class OrganizationDto {
    private Long Id;
    private String nombre;
    private String cuit;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaAlta;
    private String clave;
}
