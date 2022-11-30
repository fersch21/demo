package com.trabajointegrador.demo.serviceImpl;

import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Organization;
import com.trabajointegrador.demo.repository.OrganizationRepository;
import com.trabajointegrador.demo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrganizationImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRespository;

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationRespository.save(organization);
    }
    @Override
    public Organization findOrganizationFindId(Long id) {
        return organizationRespository.findById(id).orElseThrow(()-> new NotFoundException("no se cuentra la organizacion con el" + id));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        organizationRespository.delete(findOrganizationFindId(id));
        return Map.of("Message", "the organization id " + id + " was deleted successfully");
    }

    @Override
    public Organization updateOrganization(Long id, Organization organization) {
        Organization organizationFound = findOrganizationFindId(id);
        organizationFound = organization;
        return organizationRespository.save(organizationFound);
    }
    public final Integer SIZE_PAGE = 20;
    @Override
    public List<Organization> listOfOrganization(Integer page) {
        return organizationRespository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent();
    }
}