package com.example.licenses;

import com.example.common.entity.OrganizationChangeModel;
import com.example.common.util.UserContextFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

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
@Slf4j
//@EnableBinding(Sink.class)  //@EnableBing 注解告诉服务使用Sink接口中定义的通道来监听传入的消息
// @EnableDiscoveryClient   //使服务能够使用DiscoveryClient和Ribbon库
@EnableFeignClients  //使用Feign客户端
@RefreshScope
//@EnableResourceServer   //配置为OAuth2受保护资源
@EnableCircuitBreaker  //告诉spring cloud 将要为服务使用Hystrix
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})

public class LicensesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicensesApplication.class);
    }

    @Bean
    public UserContextFilter userContextFilter(){
        return new UserContextFilter();
    }


//    @StreamListener( Sink.INPUT )
//    public void loggerSink(OrganizationChangeModel organizationChangeModel){
//
//        log.info("Received an event for organization id {}",organizationChangeModel.getOrganizationId());
//    }

}
