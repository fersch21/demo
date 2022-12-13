package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.TurnoDto;
import com.trabajointegrador.demo.exception.BadRequestException;
import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Evento;
import com.trabajointegrador.demo.model.Organization;
import com.trabajointegrador.demo.model.Turno;
import com.trabajointegrador.demo.repository.TurnoRepository;
import com.trabajointegrador.demo.service.EventoService;
import com.trabajointegrador.demo.service.OrganizationService;
import com.trabajointegrador.demo.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TurnoImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    public ModelMapper modelMapper;


    @Override
    public TurnoDto createTurno(Long idEvento, Long idOrganizacion, TurnoDto dto) {
        isTurnoValid(dto.getFechaTurno());
        Evento eventoFound = eventoService.findEntityById(idEvento);
        Organization organizationFound = organizationService.findEntityById(idOrganizacion);
        isClaveCorrect(organizationFound, dto.getClaveOrganization());
        if(eventoFound.getEstado().equals("INACTIVO")) throw new BadRequestException("el evento no esta activo");
        Turno entity = modelMapper.map(dto, Turno.class);
//        entity.setPersona(personaFound);
        entity.setEvento(eventoFound);

        if(eventoFound.getPeriodicidad().equals("UNICO") && (eventoFound.getHora()!=null || eventoFound.getFecha()!=null)) {
//            entity.setHoraTurno(eventoFound.getHora());
//            entity.setFechaTurno(eventoFound.getFecha());
        throw new BadRequestException("El evento al ser unico, se informa solo fecha y hora del mismo");
            }

        if(eventoFound.getPeriodicidad().equals("UNICO")) {
            entity.setHoraTurno(eventoFound.getHora());
            entity.setFechaTurno(eventoFound.getFecha());
        }
        Turno turnoSaved = turnoRepository.save(entity);
        TurnoDto turnoDto = modelMapper.map(turnoSaved, TurnoDto.class);
        turnoDto.setClaveOrganization(null);
        return turnoDto;
    }

    @Override
    public TurnoDto findTurnoFindId(Long id) {
        Turno turno = findEntityById(id);
        TurnoDto dto = modelMapper.map(turno, TurnoDto.class);
        dto.setClaveOrganization(null);
        return dto;
    }

    public Turno findEntityById(Long id) {
        return turnoRepository.findById(id).orElseThrow(() -> new NotFoundException("no se cuentra el turno " + id));
    }

    @Override
    public Map<String, String> deleteById(ClaveForm clave, Long id, Long idOrganization) {
        Turno turnoFound = findEntityById(id);
        Organization organizationFound = organizationService.findEntityById(idOrganization);
        isClaveCorrect(organizationFound, clave.getClave());
        turnoRepository.delete(turnoFound);
        return new HashMap<String, String>(){{put("Message", "turno id " + id + " ha sido elimindo");}};
    }


    @Override
    public TurnoDto updateTurno (Long id, TurnoDto dto, Long idOrganization) {
        Turno turnoFound = findEntityById(id);
        isTurnoValid(dto.getFechaTurno());
        Organization organizationFound = organizationService.findEntityById(idOrganization);
        isClaveCorrect(organizationFound, turnoFound.getPersona().getClave());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, turnoFound);
        Turno turnoSaved = turnoRepository.save(turnoFound);
        TurnoDto turnoDto = modelMapper.map(turnoSaved, TurnoDto.class);
        turnoDto.setClaveOrganization(null);
        return turnoDto;
    }

    public final Integer SIZE_PAGE = 20;

    @Override
    public List<TurnoDto> listOfTurno(Integer page) {
        List<TurnoDto> dto =turnoRepository.findAll(PageRequest.of(page, SIZE_PAGE)).map(turno -> modelMapper.map(turno, TurnoDto.class)).getContent();
        dto.forEach(d-> d.setClaveOrganization(null));
        return dto;
    }
    public void isClaveCorrect(Organization organization, String clave){
        if(!organization.getClave().equals(clave)) throw new BadRequestException("clave incorrecta");
    }
    public void isTurnoValid(LocalDate fecha){
        if(fecha.isBefore(LocalDate.now())) throw new BadRequestException("el turno se encuentra vencido, debe ser posterior al día de la fecha");
    }
}

