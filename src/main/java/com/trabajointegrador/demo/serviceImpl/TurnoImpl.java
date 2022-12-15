package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.TurnoDto;
import com.trabajointegrador.demo.exception.BadRequestException;
import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.*;
import com.trabajointegrador.demo.repository.EventoRepository;
import com.trabajointegrador.demo.repository.OrganizationRepository;
import com.trabajointegrador.demo.repository.TurnoRepository;
import com.trabajointegrador.demo.service.EventoService;
import com.trabajointegrador.demo.service.OrganizationService;
import com.trabajointegrador.demo.service.PersonasService;
import com.trabajointegrador.demo.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TurnoImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PersonasService personasService;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public TurnoDto createTurno(Long idEvento, Long idPersona, TurnoDto dto) {
        if (dto.getFechaTurno() != null) isTurnoValid(dto.getFechaTurno());
        Personas personaFound = personasService.findEntityById(idPersona);
        Evento eventoFound = eventoService.findEntityById(idEvento);
        if (personaFound.getTurnos().stream().anyMatch(turno -> turno.getEvento() == eventoFound))
            throw new BadRequestException("la persona ya esta inscripto a ese evento");
        if (eventoFound.getEstado().equals(Estado.INACTIVO)) throw new BadRequestException("el evento no esta activo");
        Turno turnoEntity = modelMapper.map(dto, Turno.class);
        if (eventoFound.getPeriodicidad().equals(Periodicidad.UNICO) && (dto.getHoraTurno() != null || dto.getFechaTurno() != null)) {
            throw new BadRequestException("El evento es UNICO no se debe ingresar fecha y hora");
        }
        if (eventoFound.getPeriodicidad().equals(Periodicidad.RECURRENTE) && (dto.getHoraTurno() == null || dto.getFechaTurno() == null)) {
            throw new BadRequestException("El evento es RECURRENTE debe ingresar fecha y hora");
        }
        if (eventoFound.getPeriodicidad().equals(Periodicidad.UNICO)) {
            turnoEntity.setHoraTurno(eventoFound.getHora());
            turnoEntity.setFechaTurno(eventoFound.getFecha());
            turnoEntity.setClaveAutogenerada(UUID.randomUUID().toString());
        } else {
            turnoEntity.setClaveAutogenerada(null);
        }
        turnoEntity.setEvento(eventoFound);
        turnoEntity.setPersona(personaFound);
        Turno turnoSaved = turnoRepository.save(turnoEntity);
        TurnoDto turnoDto = modelMapper.map(turnoSaved, TurnoDto.class);
        return turnoDto;
    }

    @Override
    public TurnoDto findTurnoFindId(Long id) {
        Turno turno = findEntityById(id);
        TurnoDto dto = modelMapper.map(turno, TurnoDto.class);
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
        return new HashMap<String, String>() {{
            put("Message", "turno id " + id + " ha sido elimindo");
        }};
    }


    @Override
    public TurnoDto updateTurno(Long id, TurnoDto dto, Long idOrganization) {
        Turno turnoFound = findEntityById(id);
        isTurnoValid(dto.getFechaTurno());
        Organization organizationFound = organizationService.findEntityById(idOrganization);
        isClaveCorrect(organizationFound, turnoFound.getPersona().getClave());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, turnoFound);
        turnoFound.setClaveAutogenerada(UUID.randomUUID().toString());
        Turno turnoSaved = turnoRepository.save(turnoFound);
        TurnoDto turnoDto = modelMapper.map(turnoSaved, TurnoDto.class);

        return turnoDto;
    }

    public final Integer SIZE_PAGE = 20;

    @Override
    public List<TurnoDto> listOfTurno(Integer page) {
        List<TurnoDto> dto = turnoRepository.findAll(PageRequest.of(page, SIZE_PAGE)).map(turno -> modelMapper.map(turno, TurnoDto.class)).getContent();
//        dto.forEach(d-> d.setClaveOrganization(null));
        return dto;
    }

    public void isClaveCorrect(Organization organization, String clave) {
        if (!organization.getClave().equals(clave)) throw new BadRequestException("clave incorrecta");
    }

    public void isTurnoValid(LocalDate fecha) {
        if (fecha.isBefore(LocalDate.now()))
            throw new BadRequestException("el turno se encuentra vencido, debe ser posterior al día de la fecha");
    }

    @Override
    public List<TurnoDto> getAll(Long idOrg, Long idEvento, String estado) {
        Organization organizationFound = organizationService.findEntityById(idOrg);
        Evento eventoFound = eventoService.findEntityById(idEvento);
        if (!organizationFound.getEventos().contains(eventoFound)) throw new BadRequestException("no organizacion no posee este evento");
        List<Turno> turnos = new ArrayList<>();
        try {
            if (Estado.valueOf(estado).equals(Estado.ACTIVO))
            return  eventoFound.getTurnos().stream().filter(turno -> turno.getEstado().equals(Estado.ACTIVO)).map(dto -> modelMapper.map(dto, TurnoDto.class)).collect(Collectors.toList());
            return eventoFound.getTurnos().stream().filter(turno -> turno.getEstado().equals(Estado.INACTIVO)).map(dto -> modelMapper.map(dto, TurnoDto.class)).collect(Collectors.toList());

        }
        catch (Exception ex){
            throw new BadRequestException("el estado es inexistente, debe ser ACTIVO O INACTIVO");
        }
          }
}


