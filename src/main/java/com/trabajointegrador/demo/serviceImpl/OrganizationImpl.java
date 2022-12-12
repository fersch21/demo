package com.trabajointegrador.demo.serviceImpl;


import com.trabajointegrador.demo.dto.ClaveForm;
import com.trabajointegrador.demo.dto.ClaveUpdate;
import com.trabajointegrador.demo.dto.OrganizationDto;
import com.trabajointegrador.demo.exception.BadRequestException;
import com.trabajointegrador.demo.exception.MessageCustom;
import com.trabajointegrador.demo.exception.NotFoundException;
import com.trabajointegrador.demo.model.Organization;
import com.trabajointegrador.demo.repository.OrganizationRepository;
import com.trabajointegrador.demo.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OrganizationImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRespository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public OrganizationDto createOrganization(OrganizationDto dto) {
        if(organizationRespository.existsByCuit(dto.getCuit())) throw new BadRequestException("el cuit " + dto.getCuit() + " ya se encuentra en uso");
        Organization entity = modelMapper.map(dto, Organization.class);
        Organization orgSaved = organizationRespository.save(entity);
        orgSaved.setClave(null);
        OrganizationDto organizationDto = modelMapper.map(orgSaved, OrganizationDto.class);
        return organizationDto;
    }
    @Override
    public OrganizationDto findOrganizationFindId(Long id) {
        Organization organization = findEntityById(id);
        organization.setClave(null);
        return modelMapper.map(organization, OrganizationDto.class);
    }



    public Organization findEntityById(Long id){
        return organizationRespository.findById(id).orElseThrow(()-> new NotFoundException("no se cuentra la organizacion con el " + id));
    }

    @Override
    public Map<String, String> deleteById(Long id, ClaveForm clave) {
        Organization organizationFound = findEntityById(id);
        isClaveCorrect(organizationFound, clave.getClave());
        organizationRespository.delete(organizationFound);
        return Map.of("Message", "the organization id " + id + " was deleted successfully");
    }

    @Override
    public OrganizationDto updateOrganization(Long id, OrganizationDto dto) {
        Organization organizationFound = findEntityById(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, organizationFound);
        Organization organizationSaved = organizationRespository.save(organizationFound);
        organizationSaved.setClave(null);
        return modelMapper.map(organizationSaved, OrganizationDto.class);
    }
    public final Integer SIZE_PAGE = 20;
    @Override
    public List<OrganizationDto> listOfOrganization(Integer page) {
        List<OrganizationDto> dtoList = organizationRespository.findAll(PageRequest.of(page, SIZE_PAGE)).map(organization -> modelMapper.map(organization, OrganizationDto.class)).getContent();
        dtoList.stream().forEach((org) -> org.setClave(null));
        return dtoList;

    }
    @Override
    public MessageCustom updateClave(Long id, ClaveUpdate claveUpdate){
        Organization organizationFoundById = findEntityById(id);
        isClaveCorrect(organizationFoundById, claveUpdate.getOldClave());
        organizationFoundById.setClave(claveUpdate.getNewClave());
        organizationRespository.save(organizationFoundById);
        return new MessageCustom("clave actualizada correctamente", "200");
    }
    @Override
    public void isClaveCorrect(Organization organization, String clave){
        if(!organization.getClave().equals(clave)) throw new BadRequestException("la clave es incorrecta");
    }
}
