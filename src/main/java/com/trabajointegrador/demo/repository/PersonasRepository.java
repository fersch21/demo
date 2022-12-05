package com.trabajointegrador.demo.repository;

import com.trabajointegrador.demo.model.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonasRepository extends JpaRepository <Personas, Long> {
}
