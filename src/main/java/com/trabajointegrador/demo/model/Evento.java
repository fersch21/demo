package com.trabajointegrador.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message= "el nombre no debe estar vacio")
    private String nombre;
    @NotBlank(message= "la ubicación no debe estar vacia")
    private String ubicacion;
    @Enumerated(value = EnumType.STRING)
    private Estado estado;
    @CreationTimestamp
    private LocalDate fechaAlta;
    @Enumerated(value = EnumType.STRING)
    private Periodicidad periodicidad;
    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organization organization;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "evento")
    private List<Turno> turnos;
    private LocalDate fecha;
    private LocalTime hora;
}
