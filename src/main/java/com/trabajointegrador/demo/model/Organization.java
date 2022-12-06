package com.trabajointegrador.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.result.UpdateCountOutput;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
    public class Organization {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
        @Column(unique = true)
        private String cuit;
        private String direccion;
        private String telefono;
        @NotBlank(message = "el mail no puede estar vacio")
        private String email;
        @CreationTimestamp
        private LocalDate fechaAlta;
        @NotBlank(message = "la clave no puede estar vacia")
        @Pattern(regexp="^([a-zA-Z1-8]{20,40})", message="la clave solo puede tener 20 - 40 caracteres alfanumericos")
        private String clave;
        @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "organization")
        private List<Evento> eventos;
    }

