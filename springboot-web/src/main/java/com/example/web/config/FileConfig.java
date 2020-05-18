package com.example.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 本地图片映射配置
 */
@Configuration
public class FileConfig extends WebMvcConfigurerAdapter  {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的image目录下，访问路径如：http://localhost:8081/context/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中image表示访问的前缀。"file:D:/image/"是文件真实的存储路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:D:/image/");
    }

}