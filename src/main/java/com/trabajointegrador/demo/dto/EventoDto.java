package com.trabajointegrador.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trabajointegrador.demo.model.Estado;
import com.trabajointegrador.demo.model.Periodicidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Enumerated;
import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class EventoDto {
    private Long id;
    private String nombre;
    private String ubicacion;
    private Estado activo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaAlta;
    private Periodicidad periodicidad;
    @JsonProperty("clave organizacion")
    private String claveOrganizacion;
}
