package com.trabajointegrador.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class OrganizationDto {
    private Long Id;
    private String nombre;
    @NotBlank(message = "el cuit no puede estar vacio")
    private String cuit;
    private String direccion;
    private String telefono;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaAlta;
    @NotBlank(message = "la clave no puede estar vacia")
    @Pattern(regexp="^([a-zA-Z1-8]{20,40})", message="la clave solo puede tener 20 - 40 caracteres alfanumericos")
    private String clave;
}
