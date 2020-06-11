package com.example.licenses.redis;

import com.example.licenses.config.ServiceConfig;
import com.netflix.discovery.converters.Auto;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:25 on 2020/6/11
 * @version V0.1
 * @classNmae RedisConfiguration
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    private ServiceConfig serviceConfig;

    /**
     * 配置redis 数据库链接
     * step1 创建链接工厂
     * @return
     */

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        RedisStandaloneConfiguration redisConfiguration = jedisConnectionFactory.getStandaloneConfiguration();
        redisConfiguration.setHostName(serviceConfig.getRedisServer());
        redisConfiguration.setPort(serviceConfig.getRedisPort());
        return jedisConnectionFactory;
    }

    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
