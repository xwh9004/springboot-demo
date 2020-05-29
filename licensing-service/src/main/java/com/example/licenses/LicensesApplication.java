package com.example.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:35 on 2020/5/27
 * @version V0.1
 * @classNmae LicensesApplication
 */
@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient   //使服务能够使用DiscoveryClient和Ribbon库
public class LicensesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicensesApplication.class);
    }
}
