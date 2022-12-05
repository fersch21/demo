package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.model.Evento;
import com.trabajointegrador.demo.model.Periodicidad;

import java.util.List;
import java.util.Map;

public interface EventoService {
    Evento createEvento (Evento evento);
    Evento findEventoFindId(Long id);
    Map<String, String> deleteById(Long id);
    Evento updateEvento(Long id, Evento evento);
    List<Evento> listOfEvento(Integer page);

//    debo implementar en enum periodicidad.-
}

