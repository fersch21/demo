package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Turno;
import com.trabajointegrador.demo.repository.TurnoRepository;
import com.trabajointegrador.demo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TurnoImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public Turno createTurno (Turno turno) {
        return turnoRepository.save(turno);
    }
    @Override
    public Turno findTurnoFindId(Long id) {
        return turnoRepository.findById(id).orElseThrow(()-> new NotFoundException("no se cuentra el turno con el" + id));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        turnoRepository.delete(findTurnoFindId(id));
        return Map.of("Message", "turno id " + id + " was deleted successfully");
    }

    @Override
    public Turno updateTurno(Long id, Turno turno) {
        Turno turnoFound = findTurnoFindId(id);
        turnoFound = turno;
        return turnoRepository.save(turnoFound);
    }
    public final Integer SIZE_PAGE = 20;
    @Override
    public List<Turno> listOfTurno(Integer page) {
        return turnoRepository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent();
    }
}

