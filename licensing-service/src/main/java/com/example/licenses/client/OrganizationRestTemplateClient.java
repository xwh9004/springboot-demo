package com.example.licenses.client;

import com.example.common.entity.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:57 on 2020/6/9
 * @version V0.1
 * @classNmae OrganizationRestTemplateClient
 */
@Slf4j
@Component
public class OrganizationRestTemplateClient {

    @Qualifier("oAuth2RestTemplate")
    @Autowired
    private OAuth2RestTemplate restTemplate;

    public Organization getOrganization(String organizationId){

        log.info("通过zuulServer 调用组织服务");
        String serviceUri ="http://localhost:5555/api/organization/v1/organizations/{organizationId}";
        ResponseEntity<Organization> restExchange = restTemplate
                .exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);

        return restExchange.getBody();
    }

}
