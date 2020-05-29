package com.example.licenses.services;

import com.example.common.entity.Organization;
import com.example.licenses.client.OrganizationDiscoveryClient;
import com.example.licenses.client.OrganizationFeignClient;
import com.example.licenses.config.ServiceConfig;
import com.example.licenses.model.License;
import com.example.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:49 on 2020/5/28
 * @version V0.1
 * @classNmae LicenseService
 */
@Service
public class LicenseService {
    @Autowired
    ServiceConfig serviceConfig;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private OrganizationDiscoveryClient organizationClient;
    //Fegin客户端
    @Autowired
    private OrganizationFeignClient organizationFeignlient;

    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        return license.withComment(serviceConfig.getExampleProperty());
    }

    public List<License> getLicenseByOrg(String organizationId) {
        List<License> list = licenseRepository.findByOrganizationId(organizationId);
        return list;
    }

    public License saveLicense(License license) {
        license.withLicenseId(UUID.randomUUID().toString()).withLicenseType("test");
        licenseRepository.save(license);
        return license;
    }

    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization org = retrieveOrgInfo(clientType);
        return license
                .withOrganizationName(org.getName())
                .withContactName(org.getContactName())
                .withContactEmail(org.getContactEmail())
                .withContactPhone(org.getContactPhone())
                .withComment(serviceConfig.getExampleProperty());
    }

    private Organization retrieveOrgInfo(String clientType) {
//        return organizationClient.getOrganization(clientType);
        return organizationFeignlient.getOrganization(clientType);
    }
}
