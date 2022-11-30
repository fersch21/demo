package com.trabajointegrador.demo.controller;

import com.trabajointegrador.demo.model.Organization;
import com.trabajointegrador.demo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/{id}")
    public ResponseEntity<Organization> findById(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.findOrganizationFindId(id));
    }
    @PostMapping
    public ResponseEntity<Organization> create(@RequestBody Organization organization){
        return ResponseEntity.status(201).body(organizationService.createOrganization(organization));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.deleteById(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<Organization> updateById(@PathVariable Long id,@RequestBody Organization organization){
        return ResponseEntity.ok(organizationService.updateOrganization(id, organization));
    }
    @GetMapping("/{page}")
    public ResponseEntity<List<Organization>> getListPage(@RequestParam Integer page){
        return ResponseEntity.ok(organizationService.listOfOrganization(page));
    }
}
