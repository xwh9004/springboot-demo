package com.example.licenses.controller;

import com.example.licenses.support.UserContextHolder;
import com.example.licenses.model.License;
import com.example.licenses.services.LicenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:40 on 2020/5/27
 * @version V0.1
 * @classNmae LicenseServiceController
 */
@Slf4j
@RequestMapping( value = "/v1/organizations/{organizationId}/licenses" )
@RestController
public class LicenseServiceController {

    @Autowired
    LicenseService licenseService;

    @RequestMapping( value = "/{licenseId}", method = RequestMethod.GET )
    public License getLicense(@PathVariable( value = "organizationId" ) String organizations,
                              @PathVariable( value = "licenseId" ) String licenseId) {
        log.info("LicenseServiceController CorrelationId ={} ", UserContextHolder.getContext().getCorrelationId());
        return licenseService.getLicense(organizations, licenseId);
    }

    @RequestMapping( value = "/save/{productName}/{comment}", method = RequestMethod.POST )
    public String saveLicense(@PathVariable( value = "organizationId" ) String organizationId,
                              @PathVariable( value = "productName" ) String productName,
                              @PathVariable( value = "comment" ) String comment) {
        License license = new License()
                .withComment(comment)
                .withProductName(productName)
                .withOrganizationId(organizationId);
        return licenseService.saveLicense(license).getLicenseId();
    }
    @RequestMapping( value = "", method = RequestMethod.GET )
    public List<License> getLicensesByOrgId(
            @PathVariable( value = "organizationId" ) String organizationId) {
        log.info("LicenseServiceController CorrelationId ={} ", UserContextHolder.getContext().getCorrelationId());
        return licenseService.getLicensesByOrg(organizationId);
    }
}
