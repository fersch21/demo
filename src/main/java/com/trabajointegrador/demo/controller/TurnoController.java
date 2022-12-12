package com.trabajointegrador.demo.controller;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.TurnoDto;
import com.trabajointegrador.demo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @PostMapping("/evento/{idEvento}/persona/{idPersona}")
    public ResponseEntity<TurnoDto> create(@PathVariable Long idEvento, @PathVariable Long idPersona, @Valid @RequestBody TurnoDto turno){
        return ResponseEntity.status(201).body(turnoService.createTurno(idEvento, idPersona, turno));
    }
    @DeleteMapping("/{id}/persona/{idPersona}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id, @PathVariable Long idPersona,@RequestBody ClaveForm clave){
        return ResponseEntity.ok(turnoService.deleteById(clave, id, idPersona));
    }
    @PutMapping("/{id}/persona/{idPersona}")
    public ResponseEntity<TurnoDto> updateById(@PathVariable Long id,@PathVariable Long idPersona, @RequestBody TurnoDto turno){
        return ResponseEntity.ok(turnoService.updateTurno(id, turno, idPersona));
    }
    @GetMapping
    public ResponseEntity<List<TurnoDto>> getListPage(@RequestParam Integer page){
        return ResponseEntity.ok(turnoService.listOfTurno(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.findTurnoFindId(id));
    }
}
