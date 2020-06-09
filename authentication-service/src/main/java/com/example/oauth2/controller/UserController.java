package com.example.oauth2.controller;

import com.example.oauth2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 10:40 on 2020/6/8
 * @version V0.1
 * @classNmae UserController
 */
@Slf4j
@RestController
public class UserController {


    @RequestMapping( value = "/user", produces = "application/json" )
    public Map<String, Object> user(OAuth2Authentication user, HttpServletRequest request) {
        log.info("UserController get user receive from ={}:{}:{}",request.getRemoteHost(),request.getRemotePort(),request.getRemoteAddr());
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities",
                AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

        return userInfo;
    }
}
