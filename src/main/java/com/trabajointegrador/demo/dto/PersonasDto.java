package com.trabajointegrador.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonasDto {
    private Long id;
    private String name;
    private String apellido;
    private String dni;
    private String clave;
}




