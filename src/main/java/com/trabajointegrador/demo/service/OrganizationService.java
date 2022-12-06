package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.dto.OrganizationDto;
import com.trabajointegrador.demo.model.Organization;

import java.util.List;
import java.util.Map;

public interface OrganizationService {
     OrganizationDto createOrganization (OrganizationDto organization);
     OrganizationDto findOrganizationFindId(Long id);
     Map<String, String> deleteById(Long id);
     OrganizationDto updateOrganization(Long id, OrganizationDto organization);
     List<OrganizationDto> listOfOrganization(Integer page);
}

