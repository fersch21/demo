package com.trabajointegrador.demo.controller;


import com.trabajointegrador.demo.dto.PersonasDto;
import com.trabajointegrador.demo.service.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personas")
public class PersonasController {
    @Autowired
    private PersonasService personasService;

    @PostMapping
    public ResponseEntity<PersonasDto> create(@Valid @RequestBody PersonasDto personas){
        return ResponseEntity.status(201).body(personasService.createPersonas(personas));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(personasService.deleteById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonasDto> updateById(@PathVariable Long id,@RequestBody PersonasDto personas){
        return ResponseEntity.ok(personasService.updatePersonas(id, personas));
    }
    @GetMapping
    public ResponseEntity<List<PersonasDto>> getListPage(@RequestParam Integer page){
        return ResponseEntity.ok(personasService.listOfPersonas(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonasDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(personasService.findPersonasFindId(id));
    }
}

