package com.trabajointegrador.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trabajointegrador.demo.config.CustomLocalDateDeserializer;
import com.trabajointegrador.demo.model.Estado;
import com.trabajointegrador.demo.model.Periodicidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class EventoDto {
    private Long id;
    private String nombre;
    private String ubicacion;
    private Estado estado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaAlta;
    private Periodicidad periodicidad;
    @NotBlank(message = "no puede estar vacio")
    @JsonProperty("clave organizacion")
    private String claveOrganizacion;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate fecha;
    private LocalTime hora;
}
