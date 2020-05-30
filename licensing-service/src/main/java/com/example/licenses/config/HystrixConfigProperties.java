package com.example.licenses.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "hystrix")
@Component
public class HystrixConfigProperties {

    public String fallbackMethod;

    private String threadPoolKey;
}
