package com.trabajointegrador.demo.repository;

import com.trabajointegrador.demo.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository <Organization, Long> {
    boolean existsByCuit(String cuit);

    Optional<Organization> findByClave(String claveOrganizacion);
}
