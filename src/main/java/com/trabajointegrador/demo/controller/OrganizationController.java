package com.trabajointegrador.demo.controller;

import com.trabajointegrador.demo.dto.OrganizationDto;
import com.trabajointegrador.demo.model.Organization;
import com.trabajointegrador.demo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> create(@Valid @RequestBody OrganizationDto organization){
        return ResponseEntity.status(201).body(organizationService.createOrganization(organization));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.deleteById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto> updateById(@PathVariable Long id,@RequestBody OrganizationDto organization){
        return ResponseEntity.ok(organizationService.updateOrganization(id, organization));
    }
    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getListPage(@RequestParam Integer page){
        return ResponseEntity.ok(organizationService.listOfOrganization(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.findOrganizationFindId(id));
    }
}
