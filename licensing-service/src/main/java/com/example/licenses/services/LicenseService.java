package com.example.licenses.services;

import com.example.common.entity.Organization;
import com.example.common.util.RandUtil;
import com.example.licenses.client.OrganizationDiscoveryClient;
import com.example.licenses.client.OrganizationFeignClient;
import com.example.licenses.config.HystrixConfigProperties;
import com.example.licenses.config.ServiceConfig;
import com.example.licenses.filter.UserContextHolder;
import com.example.licenses.model.License;
import com.example.licenses.repository.LicenseRepository;
import com.netflix.hystrix.HystrixTimerThreadPoolProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@Slf4j
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

    @Autowired
    private  HystrixConfigProperties hystrixConfigProperties;

    /**
     * 使用@HystrixCommand注解会使用Hystrix断路器包装getLicensesByOrg方法
     * 并且添加容错方法
     * @param organizationId
     * @return
     */
    @HystrixCommand(
            fallbackMethod ="buildFallbackLicenseList",
            threadPoolKey = "licenseByOrgThreadPool",   //定义独立的线程池
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "15"),  //将改配置移动到application.yml 中
                    @HystrixProperty(name = "maxQueueSize",value = "10")
            },
            commandProperties = {
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "75"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "7000"),
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value = "15000"),
                    @HystrixProperty(name="metrics.rollingStats.numBuckets",value = "5"),
            }
    )
    public List<License> getLicensesByOrg(String organizationId) {
        log.info("LicenseService getLicensesByOrg CorrelationId ={} ", UserContextHolder.getContext().getCorrelationId());
        log.info(" LicenseService getLicensesByOrg Thread Name ={},id = {}",Thread.currentThread().getName(),Thread.currentThread().getId());
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
        log.info("LicenseService getLicense CorrelationId = {}", UserContextHolder.getContext().getCorrelationId());
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
     * retrieveOrgInfo 方法被getLicense调用，线程调用信息与getLicense线程是一个线程，这里配置HystrixCommand似乎不起作用
     * 并没有产生Hystrix的代理调用
     * @param orgId
     * @return
     */
    @HystrixCommand(
            threadPoolKey = "retrieveOrgInfoThreadPool",   //定义独立的线程池
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "15"),  //将改配置移动到application.yml 中
                    @HystrixProperty(name = "maxQueueSize",value = "10")
            }
            ,commandProperties = {
            //  @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "10000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "75"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "7000"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value = "15000"),
            @HystrixProperty(name="metrics.rollingStats.numBuckets",value = "5")
    }
    )
    public Organization retrieveOrgInfo(String orgId) {
        log.info(" LicenseService retrieveOrgInfo Thread Name ={},id = {}",Thread.currentThread().getName(),Thread.currentThread().getId());
        log.info("LicenseService retrieveOrgInfo CorrelationId ={} ", UserContextHolder.getContext().getCorrelationId());
        //不过没有修改配置ribbon或者feign的超时i时间 这里不是用FeignClient的原因是FeginClient的默认有5s超时的机制
        return organizationClient.getOrganization(orgId);
//        return organizationFeignlient.getOrganization(orgId);
    }

    private List<License> buildFallbackLicenseList(String orgId){
        List list = new ArrayList();
        License license = new License()
                .withLicenseId("0000000000000-0000000000")
                .withOrganizationId(orgId)
                .withProductName("sorry no licensing information currently available");
        list.add(license);
        return list;
    }

}
