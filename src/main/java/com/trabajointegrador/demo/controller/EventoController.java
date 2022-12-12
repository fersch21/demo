package com.trabajointegrador.demo.controller;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.EventoDto;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @PostMapping("/organization/{idOrg}")
    public ResponseEntity<EventoDto> createEntity(@RequestBody @Valid EventoDto eventoDto, @PathVariable Long idOrg){
        return ResponseEntity.status(201).body(eventoService.createEvento(idOrg, eventoDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(eventoService.findEventoFindId(id));
    }
    @DeleteMapping("/{id}/organization/{idOrg}")
    public ResponseEntity<MessageCustom> deleteById(@PathVariable Long idOrg, @PathVariable Long id, @RequestBody ClaveForm claveForm){
        return ResponseEntity.ok(eventoService.deleteById(idOrg, id, claveForm));
    }
    @PutMapping("/{id}/organization/{idOrg}")
    public ResponseEntity<EventoDto> updateById(@PathVariable Long idOrg, @PathVariable Long id, @RequestBody @Valid EventoDto eventoDto){
        return ResponseEntity.ok(eventoService.updateEvento(idOrg, id, eventoDto));
    }
    @GetMapping
    public ResponseEntity<List<EventoDto>> getListPage(@RequestParam Integer page){
        return ResponseEntity.ok(eventoService.listOfEvento(page));
    }
}
