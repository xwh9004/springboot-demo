package com.example.organization.service;

import com.example.common.entity.Organization;
import com.example.common.util.RandUtil;
import com.example.organization.events.source.SimpleSourceBean;
import com.example.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    SimpleSourceBean simpleSourceBean;


    public Organization getOrganization(String organizationId){
//        RandUtil.randomlyRunLong(3);
        return organizationRepository.findById(organizationId).get();
    }

    public Organization saveOrganization(Organization organization){
        organizationRepository.save(organization);
        simpleSourceBean.publishOrgChange("SAVE",organization.getOrganizationId());
        return organization;
    }

    public void deleteOrganization(String organizationId){
        organizationRepository.deleteById(organizationId);
    }
}
