package com.trabajointegrador.demo.repository;

import com.trabajointegrador.demo.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository <Turno, Long> {
}
