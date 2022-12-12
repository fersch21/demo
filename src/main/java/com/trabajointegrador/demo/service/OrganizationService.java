package com.trabajointegrador.demo.service;

import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.ClaveUpdate;
import com.trabajointegrador.demo.dto.OrganizationDto;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.model.Organization;

import java.util.List;
import java.util.Map;

public interface OrganizationService {
     OrganizationDto createOrganization (OrganizationDto organization);
     OrganizationDto findOrganizationFindId(Long id);
     Map<String, String> deleteById(Long id, ClaveForm claveForm);
     OrganizationDto updateOrganization(Long id, OrganizationDto organization);
     List<OrganizationDto> listOfOrganization(Integer page);
     Organization findEntityById (Long id);

     MessageCustom updateClave (Long id, ClaveUpdate claveUpdate);

     void isClaveCorrect(Organization organization, String clave);

}

