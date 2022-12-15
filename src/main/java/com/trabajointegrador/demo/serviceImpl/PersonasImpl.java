package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.ClaveUpdate;
import com.trabajointegrador.demo.dto.PersonasDto;
import com.trabajointegrador.demo.exception.BadRequestException;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Personas;
import com.trabajointegrador.demo.repository.PersonasRepository;
import com.trabajointegrador.demo.service.PersonasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonasImpl implements PersonasService {

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonasDto createPersonas (PersonasDto dto)
    {
        dto.setId(null);
        Personas entity = modelMapper.map(dto, Personas.class);
        Personas personasSaved = personasRepository.save(entity);
        personasSaved.setClave(null);
        PersonasDto personasDto = modelMapper.map(personasSaved, PersonasDto.class);
        return personasDto;
    }
    @Override

    public PersonasDto findPersonasFindId(Long id) {
        Personas personas = findEntityById(id);
        personas.setClave(null);
        return modelMapper.map(personas, PersonasDto.class);
        }


    public Personas findEntityById(Long id){
            return personasRepository.findById(id).orElseThrow(()-> new NotFoundException("no se cuentra la persona " + id));
        }

    @Override

   public Map<String, String> deleteById(Long id, ClaveForm claveForm) {
        Personas personaFound = findEntityById(id);
        isClaveCorrect(personaFound, claveForm.getClave());
        personasRepository.delete(personaFound);
        return new HashMap<String, String>(){{put("Message", "la persona id " + id + " ha sido eliminada");}};
    }

    @Override
    public PersonasDto updatePersonas(Long id, PersonasDto dto)  {
        dto.setId(null);
        Personas personasFound = findEntityById(id);
        isClaveCorrect(personasFound, dto.getClave());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, personasFound);
        Personas personasSaved = personasRepository.save(personasFound);
        personasSaved.setClave(null);
        return modelMapper.map(personasSaved, PersonasDto.class);
    }

    @Override
    public MessageCustom updateClave(Long idPersona, ClaveUpdate claveUdpate){
        Personas personaFound = findEntityById(idPersona);
        isClaveCorrect(personaFound, claveUdpate.getOldClave());
        return new MessageCustom("clave modificada exitosamente", "200");
    }
    @Override
    public void isClaveCorrect(Personas persona, String clave){
        if(!persona.getClave().equals(clave)) throw new BadRequestException("clave incorrecta");
    }

    public final Integer SIZE_PAGE = 20;

    @Override
    public List<PersonasDto> listOfPersonas(Integer page) {
        List<PersonasDto> dtoList = personasRepository.findAll(PageRequest.of(page, SIZE_PAGE)).map(personas -> modelMapper.map(personas, PersonasDto.class)).getContent();
        dtoList.forEach(dto-> dto.setClave(null));
        return dtoList;
    }

    @Override
    public List<PersonasDto> buscarPorApellido(String apellido) {
        if(!personasRepository.existsByApellido(apellido)) throw new BadRequestException("No hay personas con ese apellido");
        List<Personas> personasList = personasRepository.findByApellido(apellido);
        return personasList.stream().map(persona-> modelMapper.map(persona, PersonasDto.class)).collect(Collectors.toList());
    }

    @Override
    public PersonasDto buscarPorDni(String dni) {
        Personas persona = personasRepository.findByDni(dni).orElseThrow(()-> new BadRequestException("No existe una persona con ese dni"));
        return modelMapper.map(persona, PersonasDto.class);
    }

    @Override
    public List<PersonasDto> getAll() {
        return personasRepository.findAll().stream().map(persona-> modelMapper.map(persona, PersonasDto.class)).collect(Collectors.toList());
    }
}
