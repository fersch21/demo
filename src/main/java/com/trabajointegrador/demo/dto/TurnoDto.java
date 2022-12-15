package com.trabajointegrador.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trabajointegrador.demo.config.CustomLocalDateDeserializer;
import com.trabajointegrador.demo.model.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TurnoDto {
    private Long id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    @Enumerated(value = EnumType.STRING)
    private Estado estado;
    private String claveAutogenerada;

}
