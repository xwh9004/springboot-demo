package com.example.organization;

import com.example.common.util.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:10 on 2020/5/29
 * @version V0.1
 * @classNmae OrganizationApplication
 */

@EntityScan("com.example.*")  //添加Entity扫描路径 解决引入三方jar中的实体类无法映射问题
@EnableResourceServer
@SpringBootApplication
public class OrganizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationApplication.class);
    }

    @Bean
    public UserContextFilter userContextFilter(){
        return new UserContextFilter();
    }
}
