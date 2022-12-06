package com.trabajointegrador.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class OrganizationDto {
    private Long Id;
    private String nombre;
    private String cuit;
    private String direccion;
    private String telefono;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaAlta;
    private String clave;
}
