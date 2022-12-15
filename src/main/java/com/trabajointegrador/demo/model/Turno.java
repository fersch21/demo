package com.trabajointegrador.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Entity @NoArgsConstructor
public class Turno {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    @Enumerated(value = EnumType.STRING)
    private Estado estado;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "evento_id")
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Personas persona;
    private String claveAutogenerada;

}