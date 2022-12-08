package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.dto.PersonasDto;
import com.trabajointegrador.demo.model.Personas;

import java.util.List;
import java.util.Map;

public interface PersonasService {

    PersonasDto createPersonas (PersonasDto personas);

    PersonasDto findPersonasFindId (Long id);


    Map<String, String> deleteById (Long id);

    PersonasDto updatePersonas (Long id, PersonasDto personas);

    List<PersonasDto> listOfPersonas (Integer page);

}
