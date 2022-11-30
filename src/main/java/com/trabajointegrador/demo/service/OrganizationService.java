package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.model.Organization;

import java.util.List;
import java.util.Map;

public interface OrganizationService {
     Organization createOrganization (Organization organization);
     Organization findOrganizationFindId(Long id);
     Map<String, String> deleteById(Long id);
     Organization updateOrganization(Long id, Organization organization);
     List<Organization> listOfOrganization(Integer page);
}

