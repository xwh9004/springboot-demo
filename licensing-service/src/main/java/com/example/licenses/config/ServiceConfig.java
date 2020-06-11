package com.example.licenses.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:57 on 2020/5/28
 * @version V0.1
 * @classNmae ServiceConfig
 */
@Data
@Component
public class ServiceConfig {

    @Value("${example.property}")
    private String exampleProperty;

    public String getExampleProperty() {
        return exampleProperty;
    }

    @Value("${signing.key}")
    private String jwtSigningKey="";
    @Value("${redis.server}")
    private String redisServer;
    @Value("${redis.port}")
    private int redisPort;

}
