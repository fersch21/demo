package com.trabajointegrador.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String ubicacion;
    private Boolean activo;
    private LocalDate fechaAlta;
    @Enumerated(value = EnumType.STRING)
    private Periodicidad periodicidad;
    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organization organization;
}
