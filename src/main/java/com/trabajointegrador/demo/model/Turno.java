package com.trabajointegrador.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Turno {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaTurno;
    private LocalDateTime horaTurno;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "evento_id")
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Personas persona;
}