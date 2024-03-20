package net.geekh.securitydemo.service.user.impl;

import net.geekh.securitydemo.SecurityDemoApplicationTests;
import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.req.RegisterReq;
import net.geekh.securitydemo.service.user.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/17 13:42
 */
public class RegisterServiceImplTest extends SecurityDemoApplicationTests {

    @Autowired
    private RegisterService registerService;

    @Test
  public void register() {

        RegisterReq registerReq = new RegisterReq();
        registerReq.setEmail("bbb111");
        registerReq.setPassword("1234");
        registerReq.setPassword2("1234");
        registerReq.setUserName("12");

        User register = registerService.register(registerReq);

    }
}