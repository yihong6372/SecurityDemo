package net.geekh.securitydemo.controller;

import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.service.LoginService;
import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/13 07:53
 */
@RestController
@RequestMapping("/user")
public class LoninController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseVo login(@RequestBody User user) {
        return loginService.login(user);
    }
}
