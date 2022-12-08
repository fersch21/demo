package com.trabajointegrador.demo.service;


import com.trabajointegrador.demo.dto.TurnoDto;
import com.trabajointegrador.demo.model.Turno;


import java.util.List;
import java.util.Map;

public interface TurnoService {

    TurnoDto createTurno (TurnoDto turno);

    TurnoDto findTurnoFindId (Long id);

    Map<String, String> deleteById (Long id);

    TurnoDto updateTurno (Long id, TurnoDto turno);

    List<TurnoDto> listOfTurno (Integer page);

}
