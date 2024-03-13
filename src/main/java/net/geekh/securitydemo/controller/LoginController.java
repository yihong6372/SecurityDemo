package net.geekh.securitydemo.controller;

import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.service.LoginService;
import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/13 07:53
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseVo login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PreAuthorize("hasAuthority('test')")
    @GetMapping("/logout")
    public ResponseVo logout() {
        return loginService.logout();
    }
}
