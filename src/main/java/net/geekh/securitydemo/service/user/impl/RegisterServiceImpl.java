package net.geekh.securitydemo.service.user.impl;

import lombok.RequiredArgsConstructor;
import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.req.RegisterReq;
import net.geekh.securitydemo.service.user.RegisterService;
import net.geekh.securitydemo.service.user.UserInfoService;
import net.geekh.securitydemo.utils.SecurityUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/17 12:56
 */

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserInfoService userInfoService;

    @Override
    public User register(RegisterReq registerReq) {

        //验证两次密码是否正确
        if (!registerReq.getPassword().equals(registerReq.getPassword2())) {
            throw new RuntimeException("两次密码不正确");

        }

        //检测用户是否已经存在
        if (userInfoService.lambdaQuery().eq(User::getEmail, registerReq.getEmail()).count() > 0) {
            throw new RuntimeException("该邮已注册");
        }
        if (userInfoService.lambdaQuery().eq(User::getUserName, registerReq.getUserName()).count() > 0) {
            System.out.println("用户名已经存在");
            throw new RuntimeException("用户已经存在");
        }

        User user = new User();
        user.setPassword(SecurityUtil.encryptPassword(registerReq.getPassword()));
        user.setEmail(registerReq.getEmail());
        user.setUserName(registerReq.getUserName());

        boolean save = userInfoService.save(user);
        return user;
    }
}
