package net.geekh.securitydemo.controller;

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
    public String hello() {
        return "Hello World!";
    }
}