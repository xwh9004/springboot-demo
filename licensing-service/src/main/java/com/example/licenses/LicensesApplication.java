package com.example.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p><b>Description:</b>
 * @RefreshScope 注解用来刷新数据源，从configsvr中重新拉取数据源
 * 1.因为ClientConfig 使用了  @LoadBalanced 因此这里可以不适用 @EnableDiscoveryClient
 * 2.@EnableFeignClients 启用Feign客户端 首先定义 OrganizationFeignClient 接口
 *
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:35 on 2020/5/27
 * @version V0.1
 * @classNmae LicensesApplication
 */
// @EnableDiscoveryClient   //使服务能够使用DiscoveryClient和Ribbon库
@EnableFeignClients  //使用Feign客户端
@SpringBootApplication
@RefreshScope
@EnableCircuitBreaker  //告诉spring cloud 将要为服务使用Hystrix
public class LicensesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicensesApplication.class);
    }
}
