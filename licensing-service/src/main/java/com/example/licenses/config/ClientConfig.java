package com.example.licenses.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 服务提供者 代理bean
 */
@Configuration
public class ClientConfig {


    @LoadBalanced    //告诉springcloud 创建一个支持Ribbonde RestTempalte
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
