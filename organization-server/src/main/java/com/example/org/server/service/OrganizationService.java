package com.example.org.server.service;

import com.example.common.entity.Organization;
import com.example.org.server.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;


    public Organization getOrganization(String organizationId){
        return organizationRepository.findById(organizationId).get();
    }

    public Organization saveOrganization(Organization organization){
        organizationRepository.save(organization);
        return organization;
    }
}
