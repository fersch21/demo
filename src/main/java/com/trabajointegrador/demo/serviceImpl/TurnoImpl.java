package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.dto.PersonasDto;
import com.trabajointegrador.demo.dto.TurnoDto;
import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Turno;
import com.trabajointegrador.demo.repository.TurnoRepository;
import com.trabajointegrador.demo.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TurnoImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    public ModelMapper modelMapper;


    @Override
    public TurnoDto createTurno(TurnoDto dto) {
        Turno entity = modelMapper.map(dto, Turno.class);
        Turno turnoSaved = turnoRepository.save(entity);
        TurnoDto turnoDto = modelMapper.map(turnoSaved, TurnoDto.class);
        return turnoDto;
    }

    @Override
    public TurnoDto findTurnoFindId(Long id) {
        Turno turno = findEntityById(id);
        return modelMapper.map(turno, TurnoDto.class);
    }

    public Turno findEntityById(Long id) {
        return turnoRepository.findById(id).orElseThrow(() -> new NotFoundException("no se cuentra el turno " + id));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        turnoRepository.delete(findEntityById(id));
        return Map.of("Message", "turno id " + id + " ha sido elimindo");
    }


    @Override
    public TurnoDto updateTurno (Long id, TurnoDto dto) {
        Turno turnoFound = findEntityById(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, turnoFound);
        Turno turnoSaved = turnoRepository.save(turnoFound);
        return modelMapper.map(turnoSaved, TurnoDto.class);
    }

    public final Integer SIZE_PAGE = 20;

    @Override
    public List<TurnoDto> listOfTurno(Integer page) {
        return turnoRepository.findAll(PageRequest.of(page, SIZE_PAGE)).map(turno -> modelMapper.map(turno, TurnoDto.class)).getContent();
    }
}

