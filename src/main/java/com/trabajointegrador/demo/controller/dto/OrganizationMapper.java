package com.trabajointegrador.demo.controller.dto;

import com.trabajointegrador.demo.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;


@Component
public class OrganizationMapper {
    @Autowired
    private ModelMapper mapper;

    public Organization entityFromDto(OrganizationDto organizationDto){
        organizationDto.setClave(null);
        organizationDto.setId(null);
        return mapper.map(organizationDto, Organization.class);
        // ModelMapper hace el mapeo que hago aca abajo
//        return new Organization(null, organizationDto.getName(), organizationDto.getPassword(), null, organizationDto.getShiftDate(), organizationDto.getShiftHourDate());
    }
    public OrganizationDto returnPartDtoFromEntity(Organization organization){
          return mapper.map(organization, OrganizationDto.class);
//        return new OrganizationDto(organization.getId(), organization.getName(), null, organization.getCreationDate(), organization.getShiftDate(), organization.getShiftHourDate());
    }
}

