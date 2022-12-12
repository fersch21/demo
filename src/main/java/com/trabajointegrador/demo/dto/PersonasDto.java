package com.trabajointegrador.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonasDto {
    private Long id;
    private String name;
    private String apellido;
    @NotBlank(message = "el dni no puede estar vacio")
    private String dni;
    @NotBlank(message = "la clave no puede estar vacia")
    @Pattern(regexp="^([a-zA-Z1-8]{20,40})", message="la clave solo puede tener 20 - 40 caracteres alfanumericos")
    private String clave;
}




