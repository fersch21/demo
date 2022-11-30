package com.trabajointegrador.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.result.UpdateCountOutput;

import javax.validation.constraints.Pattern;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

    public class Organization {
        @javax.persistence.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;
        private String nombre;
        @Column(unique = true)
        private String cuit;
        private String direccion;
        private String telefono;
        private String email;
        private LocalDate fechaAlta;
        @Pattern(regexp="^([a-zA-Z1-8]{20,40})", message="la clave solo puede tener 20 - 40 caracteres alfanumericos")
        private String clave;
        @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
        private List<Evento> eventos;
    }

