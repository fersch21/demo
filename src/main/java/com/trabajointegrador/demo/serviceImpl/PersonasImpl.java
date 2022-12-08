package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.dto.OrganizationDto;
import com.trabajointegrador.demo.dto.PersonasDto;
import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Organization;
import com.trabajointegrador.demo.model.Personas;
import com.trabajointegrador.demo.repository.PersonasRepository;
import com.trabajointegrador.demo.service.PersonasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonasImpl implements PersonasService {

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonasDto createPersonas (PersonasDto dto)
    {
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
            return personasRepository.findById(id).orElseThrow(()-> new NotFoundException("no se cuentra la persona con el " + id));
        }


    @Override

   public Map<String, String> deleteById(Long id) {
        personasRepository.delete(findEntityById(id));
        return Map.of("Message", "la persona id " + id + " ha sido eliminada");
    }

    @Override
    public PersonasDto updatePersonas(Long id, PersonasDto dto)  {
            Personas personasFound = findEntityById(id);
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(dto, personasFound);
            Personas personasSaved = personasRepository.save(personasFound);
            personasSaved.setClave(null);
            return modelMapper.map(personasSaved, PersonasDto.class);
    }

   public final Integer SIZE_PAGE = 20;

   @Override
   public List<PersonasDto> listOfPersonas(Integer page) {
        return personasRepository.findAll(PageRequest.of(page, SIZE_PAGE)).map(personas -> modelMapper.map(personas, PersonasDto.class)).getContent();
    }
}
