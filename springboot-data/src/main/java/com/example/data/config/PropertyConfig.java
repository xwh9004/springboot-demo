package com.example.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p><b>Description:</b>  TODO
 * <p><b>Company:</b>
 * 加载外部绝对路径资源文件
 * 加载不了 yml文件
 * @author created by Jesse Hsu at 15:07 on 2020/3/17
 * @version V0.1
 * @classNmae PropertyConfig
 */
@Data
@Component
@PropertySource(value = {"file:E:\\workspace\\config.properties"})   //目前不支持 yml文件格式
@ConfigurationProperties(prefix = "app")
public class PropertyConfig {

    private String name;

    private String url;
}
