package com.trabajointegrador.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ubicacion;
    @NotBlank(message = "no puede estar vacio")
    @Enumerated(value = EnumType.STRING)
    private Estado activo;
    @CreationTimestamp
    private LocalDate fechaAlta;
    @Enumerated(value = EnumType.STRING)
    private Periodicidad periodicidad;
    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organization organization;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "evento")
    private List<Turno> turnos;
}
