package com.example.organization.controller;

import com.example.common.entity.Organization;
import com.example.organization.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:38 on 2020/5/29
 * @version V0.1
 * @classNmae OrganizationController
 */
@Slf4j
@RequestMapping( "v1/organizations" )
@RestController
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @RequestMapping( "/{organizationId}" )
    public Organization getOrganization(@PathVariable( value = "organizationId" ) String organizationId) {
        log.info(" organization-service OrganizationController getOrganization");
        return organizationService.getOrganization(organizationId);
    }
    @PostMapping( "/save" )
    public Organization save(@RequestBody Organization organization) {
        return organizationService.saveOrganization(organization);
    }
}
