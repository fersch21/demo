package com.trabajointegrador.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class ClaveForm {
    @NotBlank(message = "la clave no puede estar vacia")
    @Pattern(regexp="^([a-zA-Z1-8]{20,40})", message="la clave solo puede tener 20 - 40 caracteres alfanumericos")

    private String clave;
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
