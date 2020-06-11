package com.example.licenses.client;

import com.example.common.entity.Organization;
import com.example.licenses.repository.OrganizationRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:26 on 2020/5/29
 * @version V0.1
 * @classNmae OrganizationDiscoveryClient
 */
@Slf4j
@Component
public class OrganizationDiscoveryClient {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Qualifier( "restTemplate" )
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrganizationRedisRepository organizationRedisRepository;


    public Organization getOrganization(String organizationId) {
        log.info(" OrganizationDiscoveryClient getLicensesByOrg Thread id = {}", Thread.currentThread().getId());
//        RestTemplate restTemplate = new RestTemplate();
        //获取组织服务的所有实例列表
//        List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");
//        if (instances.size() == 0) return null;
//        String serviceUri = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);
        //http 远程调用获取Organization

        //使用Ribbon 的RestTemplate 使用Eureka 的服务ID构建URL
//        String serviceUri ="http://organizationservice/v1/organizations/{organizationId}";
//        ResponseEntity<Organization> restExchange = restTemplate
//                .exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);

        Organization organization = checkRedisCache(organizationId);
        if (organization != null) {
            log.info("successfully retrieve an organization {} from the redis cache:{}", organizationId, organization);
            return organization;
        }
        log.info("unable retrieve an organization {} from the redis cache", organizationId);

        log.info("通过zuulServer 调用组织服务");
        String serviceUri = "http://localhost:5555/api/organization/v1/organizations/{organizationId}";
        ResponseEntity<Organization> restExchange = restTemplate
                .exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);
        organization = restExchange.getBody();
        if (organization != null) {
            cacheRedisCache(organization);
        }
        return restExchange.getBody();
    }

    private Organization checkRedisCache(String organizationId) {
        try {
            return organizationRedisRepository.findOrganization(organizationId);
        } catch (Exception e) {
            log.error("Error encounter while trying " +
                    "to retrieve organization {} check Redis Redis.Exception {}",organizationId, e);
            return null;
        }

    }

    private void cacheRedisCache(Organization organization) {
        try {
            organizationRedisRepository.saveOrganization(organization);
        } catch (Exception e) {
            log.error("Error encounter while trying " +
                    "to cache organization {} check Redis Redis.Exception {}", organization.getOrganizationId(), e);

        }

    }
}
