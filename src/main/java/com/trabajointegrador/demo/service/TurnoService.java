package com.trabajointegrador.demo.service;


import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.TurnoDto;
import com.trabajointegrador.demo.model.Turno;


import java.util.List;
import java.util.Map;

public interface TurnoService {

    TurnoDto createTurno (Long idEvento, Long idOrganization, TurnoDto turno);

    TurnoDto findTurnoFindId (Long id);

    Map<String, String> deleteById (ClaveForm clave, Long id, Long idOrganization);

    TurnoDto updateTurno (Long id, TurnoDto turno, Long idOrganization);

    List<TurnoDto> listOfTurno (Integer page);
    Turno findEntityById(Long id);
}
