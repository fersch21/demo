package com.trabajointegrador.demo.repository;

import com.trabajointegrador.demo.model.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonasRepository extends JpaRepository <Personas, Long> {

    List<Personas> findByApellido(String apellido);

    boolean existsByApellido(String apellido);
    Optional<Personas> findByDni(String dni);
}
