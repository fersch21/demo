package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.EventoDto;
import com.trabajointegrador.demo.exception.BadRequestException;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Evento;
import com.trabajointegrador.demo.model.Organization;
import com.trabajointegrador.demo.repository.EventoRepository;
import com.trabajointegrador.demo.repository.OrganizationRepository;
import com.trabajointegrador.demo.service.EventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventoImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private ModelMapper modelMapper;
    public final Integer SIZE_PAGE = 20;

    @Override
    public EventoDto createEvento(EventoDto eventoDto) {
        Organization organization = organizationRepository.findByClave(eventoDto.getClaveOrganizacion()).orElseThrow(()-> new BadRequestException("clave incorrecta"));
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
    public MessageCustom deleteById(Long id, ClaveForm clave) {
        Organization organizationClave = organizationRepository.findByClave(clave.getClave()).orElseThrow(()-> new BadRequestException("clave incorrecta"));
        Evento eventoFound = findEntityById(id);
        if(organizationClave.getClave()!= eventoFound.getOrganization().getClave()) throw new BadRequestException("no tiene permiso para eliminar este evento");
        eventoRepository.delete(eventoFound);
        return new MessageCustom("the Evento " + id + " was deleted successfully", "200");
    }

    @Override
    public EventoDto updateEvento(Long id, EventoDto dto) {
        Evento entityFound = findEntityById(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, entityFound);
        if(dto.getClaveOrganizacion()!= entityFound.getOrganization().getClave()) throw new BadRequestException("no tiene permiso para actualizar este evento");
        Evento eventoSaved = eventoRepository.save(entityFound);
        return modelMapper.map(eventoSaved, EventoDto.class);
    }
    @Override
    public List<EventoDto> listOfEvento(Integer page) {
        return eventoRepository.findAll(PageRequest.of(page, SIZE_PAGE)).map(evento -> modelMapper.map(evento, EventoDto.class)).getContent();
    }
}