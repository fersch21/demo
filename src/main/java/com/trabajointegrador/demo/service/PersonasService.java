package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.model.Personas;

import java.util.List;
import java.util.Map;

public interface PersonasService {

    Personas createPersonas (Personas personas);

    Personas findPersonasFindId (Long id);

    Map<String, String> deleteById (Long id);

    Personas updatePersonas (Long id, Personas personas);

    List<Personas> listOfPersonas (Integer page);

}
