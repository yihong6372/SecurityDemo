package net.geekh.securitydemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.geekh.securitydemo.SecurityDemoApplicationTests;
import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.service.LoginService;
import net.geekh.securitydemo.vo.ResponseVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/17 13:49
 */

@Slf4j
class LoginServiceImplTest extends SecurityDemoApplicationTests {

    @Autowired
    private LoginService loginService;

    @Test
    void login() {
        User user = new User();
        user.setUserName("12");
        user.setPassword("1234");
        ResponseVo vo = loginService.login(user);
        log.info("user=>{}",user);
        log.info("VO=>{}",vo);

    }

    @Test
    void logout() {
    }
}