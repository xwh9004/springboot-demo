package com.example.licenses.client;

import com.example.common.entity.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * EnableFeignClients 接口定义
 */
@FeignClient("organizationservice")
public interface OrganizationFeignClient {

    /**
     * 该端点就像是organization-service中OrganizationController 的getOrganization() 端点一样
     * @param organizationId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "v1/organizations/{organizationId}",
            consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
