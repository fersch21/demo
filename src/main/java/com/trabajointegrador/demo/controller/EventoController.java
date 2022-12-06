package com.trabajointegrador.demo.controller;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.EventoDto;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoDto> createEntity(@RequestBody EventoDto eventoDto){
        return ResponseEntity.status(201).body(eventoService.createEvento(eventoDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(eventoService.findEventoFindId(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageCustom> deleteById(@PathVariable Long id, @RequestBody ClaveForm claveForm){
        return ResponseEntity.ok(eventoService.deleteById(id, claveForm));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EventoDto> updateById(@PathVariable Long id, @RequestBody EventoDto eventoDto){
        return ResponseEntity.ok(eventoService.updateEvento(id, eventoDto));
    }
    @GetMapping
    public ResponseEntity<List<EventoDto>> getListPage(@RequestParam Integer page){
        return ResponseEntity.ok(eventoService.listOfEvento(page));
    }
}
