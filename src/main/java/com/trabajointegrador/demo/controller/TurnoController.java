package com.trabajointegrador.demo.controller;

import com.trabajointegrador.demo.dto.TurnoDto;
import com.trabajointegrador.demo.model.Turno;
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

    @PostMapping
    public ResponseEntity<TurnoDto> create(@Valid @RequestBody TurnoDto turno){
        return ResponseEntity.status(201).body(turnoService.createTurno(turno));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.deleteById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TurnoDto> updateById(@PathVariable Long id,@RequestBody TurnoDto turno){
        return ResponseEntity.ok(turnoService.updateTurno(id, turno));
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
