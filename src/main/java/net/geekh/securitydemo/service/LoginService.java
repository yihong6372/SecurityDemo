package net.geekh.securitydemo.service;

import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.vo.ResponseVo;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/13 08:12
 */
public interface LoginService {
    ResponseVo login(User user);

    ResponseVo logout();
}
