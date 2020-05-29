package com.example.organization.controller;

import com.example.common.entity.Organization;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:38 on 2020/5/29
 * @version V0.1
 * @classNmae OrganizationController
 */
@RequestMapping( "v1/organizations" )
@RestController
public class OrganizationController {

    @RequestMapping( "/{organizationId}" )
    public Organization getOrganization(@PathVariable( value = "organizationId" ) String organizationId) {
        return new Organization()
                .withName("test")
                .withContactEmail("test@1233.com")
                .withContactName("test")
                .withContactPhone("1233455667");
    }
}
