package com.example.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 10:47 on 2020/6/8
 * @version V0.1
 * @classNmae OAuth2Config
 */
//@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailService;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(passwordEncoder);
    }

    //定义哪些客户端将注册到服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
//        super.configure(clients);
        clients.inMemory()
                //配置可注册的client  id
                .withClient("eagleeye")
                //配置可注册的client  secret
                .secret(passwordEncoder.encode("thisissecret"))
                //配置可注册的client  权限
                .authorizedGrantTypes(
                        "refresh_token",
                        "password",
                        "client_credentials")
                //配置可注册的client  范围
                .scopes("webclient", "mobileclient");

    }

    //该方法定义了AuthenticationnServerConfigure中使用的不同组件。
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        super.configure(endpoints);

        endpoints.authenticationManager(authenticationManager)

        .userDetailsService(userDetailService);
    }

}
