package com.example.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 11:05 on 2020/6/8
 * @version V0.1
 * @classNmae WebSecurityConfigurer
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        super.configure(auth);
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("john.carnell")
                .password(passwordEncoder.encode("password1"))
                .roles("USERS")
                .and()
                .withUser("willian.woodward")
                .password(passwordEncoder.encode("password2"))
                .roles("USER","ADMIN");

    }
}
