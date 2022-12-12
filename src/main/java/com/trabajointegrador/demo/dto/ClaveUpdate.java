package com.trabajointegrador.demo.dto;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
public class ClaveUpdate {

    @NotBlank(message = "la clave no puede estar vacia")
    @Pattern(regexp = "^([a-zA-Z1-8]{20,40})", message = "la clave solo puede tener 20 - 40 caracteres alfanumericos")
    private String oldClave;
    @NotBlank(message = "la clave no puede estar vacia")
    @Pattern(regexp = "^([a-zA-Z1-8]{20,40})", message = "la clave solo puede tener 20 - 40 caracteres alfanumericos")
    private String newClave;

    public String getOldClave() {
        return oldClave;
    }

    public void setOldClave(String oldClave) {
        this.oldClave = oldClave;
    }

    public String getNewClave() {
        return newClave;
    }

    public void setNewClave(String newClave) {
        this.newClave = newClave;
    }
}


