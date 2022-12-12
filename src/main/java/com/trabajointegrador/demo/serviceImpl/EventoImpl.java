package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.EventoDto;
import com.trabajointegrador.demo.exception.BadRequestException;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Evento;
import com.trabajointegrador.demo.model.Organization;
import com.trabajointegrador.demo.model.Periodicidad;
import com.trabajointegrador.demo.repository.EventoRepository;
import com.trabajointegrador.demo.service.EventoService;
import com.trabajointegrador.demo.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ModelMapper modelMapper;
    public final Integer SIZE_PAGE = 20;

    @Override
    public EventoDto createEvento(Long IdOrg, EventoDto eventoDto) {
        Organization organization = organizationService.findEntityById(IdOrg);
        organizationService.isClaveCorrect(organization, eventoDto.getClaveOrganizacion());
        if(eventoDto.getEstado()== null) throw new BadRequestException("Tiene que indicar si el evento esta activo o no");
        if(eventoDto.getPeriodicidad()== null ) throw new BadRequestException("Tiene que indicar la periodicidad del evento");
        if(eventoDto.getPeriodicidad().equals(Periodicidad.UNICO) && (eventoDto.getHora() == null || eventoDto.getFecha() == null))
            throw new BadRequestException("El evento es unico, tiene que seleccionar fecha y hora");
        if(eventoDto.getPeriodicidad().equals(Periodicidad.RECURRENTE) && (eventoDto.getHora() != null || eventoDto.getFecha() != null ))
            throw new BadRequestException("El evento es periodico, no puede tener una sola fecha y horario");
        Evento eventoToSave = modelMapper.map(eventoDto, Evento.class);
        eventoToSave.setOrganization(organization);
        Evento eventoSaved = eventoRepository.save(eventoToSave);
        EventoDto dto =  modelMapper.map(eventoSaved, EventoDto.class);
        dto.setClaveOrganizacion(null);
        return dto;
    }
    @Override
    public EventoDto findEventoFindId(Long id) {
        Evento eventoFound = findEntityById(id);
        EventoDto dto =  modelMapper.map(eventoFound, EventoDto.class);
        dto.setClaveOrganizacion(null);
        return dto;
    }
    public Evento findEntityById(Long id){
        return eventoRepository.findById(id).orElseThrow(()-> new NotFoundException("no se cuentra el evento" + id));
    }

    @Override
    public MessageCustom deleteById(Long idOrg, Long id, ClaveForm clave) {
        Organization organizationFound = organizationService.findEntityById(idOrg);
        Evento eventoFound = findEntityById(id);
        organizationService.isClaveCorrect(organizationFound, clave.getClave());
        eventoRepository.delete(eventoFound);
        return new MessageCustom("El  evento " + id + " fue borrado con exito", "200");
    }

    @Override
    public EventoDto updateEvento(Long idOrg, Long id, EventoDto dto) {
        Organization organizationFound = organizationService.findEntityById(idOrg);
        Evento entityFound = findEntityById(id);
        organizationService.isClaveCorrect(organizationFound, dto.getClaveOrganizacion());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, entityFound);
        Evento eventoSaved = eventoRepository.save(entityFound);
        return modelMapper.map(eventoSaved, EventoDto.class);
    }
    @Override
    public List<EventoDto> listOfEvento(Integer page) {
        return eventoRepository.findAll(PageRequest.of(page, SIZE_PAGE)).map(evento -> modelMapper.map(evento, EventoDto.class)).getContent();
    }
}