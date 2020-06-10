package com.example.licenses.config;

import com.example.licenses.interceptor.UserContextInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * 服务提供者 代理bean
 */
@Configuration
public class ClientConfig {


    @Primary
//    @LoadBalanced    //告诉springcloud 创建一个支持Ribbonde RestTempalte
    @Bean("restTemplate")
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

//    @LoadBalanced
    @Bean("oAuth2RestTemplate")
    public OAuth2RestTemplate oAuth2RestTemplate( OAuth2ClientContext context){
        OAuth2ProtectedResourceDetails details = new ClientCredentialsResourceDetails() ;
        return new OAuth2RestTemplate(details,context);
    }
}
