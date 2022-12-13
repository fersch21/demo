package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.ClaveUpdate;
import com.trabajointegrador.demo.dto.PersonasDto;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.model.Personas;

import java.util.List;
import java.util.Map;

public interface PersonasService {

    PersonasDto createPersonas (PersonasDto personas);

    PersonasDto findPersonasFindId (Long id);

    Personas findEntityById (Long id);


    Map<String, String> deleteById (Long id, ClaveForm claveForm);

    PersonasDto updatePersonas (Long id, PersonasDto personas);

    List<PersonasDto> listOfPersonas (Integer page);

    MessageCustom updateClave(Long idPersona, ClaveUpdate claveUpdate);

    void isClaveCorrect(Personas persona, String clave);

}
