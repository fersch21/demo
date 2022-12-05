package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Personas;
import com.trabajointegrador.demo.repository.PersonasRepository;
import com.trabajointegrador.demo.service.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonasImpl implements PersonasService {

    @Autowired
    private PersonasRepository personasRepository;

    @Override
    public Personas createPersonas (Personas personas) {
        return personasRepository.save(personas);
    }
    @Override
    public Personas findPersonasFindId(Long id) {
        return personasRepository.findById(id).orElseThrow(()-> new NotFoundException("no se cuentra la persona con el" + id));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        personasRepository.delete(findPersonasFindId(id));
        return Map.of("Message", "the person id " + id + " was deleted successfully");
    }

    @Override
    public Personas updatePersonas(Long id, Personas personas) {
        Personas personasFound = findPersonasFindId(id);
        personasFound = personas;
        return personasRepository.save(personasFound);
    }
    public final Integer SIZE_PAGE = 20;
    @Override
    public List<Personas> listOfPersonas(Integer page) {
        return personasRepository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent();
    }
}

