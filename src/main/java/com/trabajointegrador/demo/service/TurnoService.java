package com.trabajointegrador.demo.service;


import com.trabajointegrador.demo.model.Turno;

import java.util.List;
import java.util.Map;

public interface TurnoService {

    Turno createTurno (Turno turno);

    Turno findTurnoFindId (Long id);

    Map<String, String> deleteById (Long id);

    Turno updateTurno (Long id, Turno turno);

    List<Turno> listOfTurno (Integer page);

}
