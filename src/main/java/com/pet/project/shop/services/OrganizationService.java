package com.pet.project.shop.services;

import com.pet.project.shop.models.Organization;
import com.pet.project.shop.repositories.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    private List<Organization> allOrganization(){
        return organizationRepository.findAll();
    }

    private Organization oneOrganization(Integer id){
        return organizationRepository.findById(id).orElseThrow();
    }

}
