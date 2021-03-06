package com.example.organization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:24 on 2020/6/8
 * @version V0.1
 * @classNmae ResourceServerConfiguration
 */
//@EnableOAuth2Client
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated();
                //antMatchers()允许开发人员限制对受保护的URL和HTTP DELETE动词的调用
             http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE,"/v1/organizations/**")
                //允许访问的角色列表
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }

}
