package net.geekh.securitydemo.service.user;

import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.req.RegisterReq;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/17 12:53
 */
public interface RegisterService {
    User register(RegisterReq registerReq);
}
