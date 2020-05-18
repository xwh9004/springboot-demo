package com.example.web.controller;

import com.example.web.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 9:52 on 2020/5/18
 * @version V0.1
 * @classNmae LoginController
 */
@Slf4j
@RestController
public class LoginController {

    @GetMapping( "/login" )
    public String login() {

        Subject subject = SecurityUtils.getSubject();
        User user = new User();
        user.setUserName("Jesse Hsu");
        user.setPassword("1234567");
        user.setRoles("admin");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            subject.login(usernamePasswordToken);
            subject.checkRole(user.getRoles());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        } catch ( AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        }
        return "登录成功";
    }


    @GetMapping( "/index" )
    public String success() {
        return "login successfully!";
    }

    @GetMapping( "/error" )
    public String error() {
        return "login error!";
    }
}
