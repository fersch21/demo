package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.EventoDto;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.model.Evento;

import java.util.List;

public interface EventoService {
    EventoDto createEvento (Long idOrg, EventoDto evento);
    EventoDto findEventoFindId(Long id);
    MessageCustom deleteById(Long idOrg, Long id, ClaveForm claveForm);
    EventoDto updateEvento(Long idOrg, Long id, EventoDto evento);
    List<EventoDto> listOfEvento(Integer page);

    Evento findEntityById (Long id);
//    debo implementar en enum periodicidad.-
}

