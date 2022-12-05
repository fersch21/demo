package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Evento;
import com.trabajointegrador.demo.repository.EventoRepository;
import com.trabajointegrador.demo.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventoImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Evento createEvento(Evento evento) {
        return eventoRepository.save(evento);
    }
    @Override
    public Evento findEventoFindId(Long id) {
        return eventoRepository.findById(id).orElseThrow(()-> new NotFoundException("no se cuentra el evento" + id));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        eventoRepository.delete(findEventoFindId(id));
        return Map.of("Message", "the Evento" + id + " was deleted successfully");
    }

    @Override
    public Evento updateEvento(Long id, Evento evento) {
        Evento eventoFound = findEventoFindId(id);
        eventoFound = evento;
        return eventoRepository.save(eventoFound);
    }
    public final Integer SIZE_PAGE = 20;
    @Override
    public List<Evento> listOfEvento(Integer page) {
        return eventoRepository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent();
    }
}