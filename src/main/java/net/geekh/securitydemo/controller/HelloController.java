package net.geekh.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/12 23:09
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('system:test:list')")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/hello2")
//    @PreAuthorize("hasAuthority('system:test:list')")
    public String hello2() {
        return "Hello World222!";
    }
}
