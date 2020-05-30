package com.example.licenses.services;

import com.example.common.entity.Organization;
import com.example.common.util.RandUtil;
import com.example.licenses.client.OrganizationDiscoveryClient;
import com.example.licenses.client.OrganizationFeignClient;
import com.example.licenses.config.ServiceConfig;
import com.example.licenses.model.License;
import com.example.licenses.repository.LicenseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
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

    /**
     * 使用@HystrixCommand注解会使用Hystrix断路器包装getLicensesByOrg方法
     * @param organizationId
     * @return
     */
    @HystrixCommand
    public List<License> getLicensesByOrg(String organizationId) {
        //随机超时
        RandUtil.randomlyRunLong(3);
        List<License> list = licenseRepository.findByOrganizationId(organizationId);

        return list;
    }

    public License saveLicense(License license) {
        license.withLicenseId(UUID.randomUUID().toString()).withLicenseType("test");
        licenseRepository.save(license);
        return license;
    }

    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization org = retrieveOrgInfo(organizationId);
        return license
                .withOrganizationName(org.getName())
                .withContactName(org.getContactName())
                .withContactEmail(org.getContactEmail())
                .withContactPhone(org.getContactPhone())
                .withComment(serviceConfig.getExampleProperty());
    }

    /**
     * 使用Hystrix断路器 并且定制超时时间
     * 其他属性值参考HystrixCommandProperties 类
     * @param orgId
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "10000")
    })
    private Organization retrieveOrgInfo(String orgId) {

        //不过没有修改配置ribbon或者feign的超时i时间 这里不是用FeignClient的原因是FeginClient的默认有5s超时的机制
        return organizationClient.getOrganization(orgId);
//        return organizationFeignlient.getOrganization(orgId);
    }


}
