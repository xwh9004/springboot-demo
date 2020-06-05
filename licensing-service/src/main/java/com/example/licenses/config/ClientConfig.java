package com.example.licenses.config;

import com.example.licenses.interceptor.UserContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * 服务提供者 代理bean
 */
@Configuration
public class ClientConfig {


    @LoadBalanced    //告诉springcloud 创建一个支持Ribbonde RestTempalte
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate=  new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if(interceptors==null){
            restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        }else{
            interceptors.add(new UserContextInterceptor());
            restTemplate.setInterceptors(interceptors);
        }
        return restTemplate;
    }
}
