package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.EventoDto;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.model.Evento;
import com.trabajointegrador.demo.model.Periodicidad;

import java.util.List;
import java.util.Map;

public interface EventoService {
    EventoDto createEvento (EventoDto evento);
    EventoDto findEventoFindId(Long id);
    MessageCustom deleteById(Long id, ClaveForm claveForm);
    EventoDto updateEvento(Long id, EventoDto evento);
    List<EventoDto> listOfEvento(Integer page);

//    debo implementar en enum periodicidad.-
}

