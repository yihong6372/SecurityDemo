package net.geekh.securitydemo.service.impl;

import net.geekh.securitydemo.domain.LoginUser;
import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.service.LoginService;
import net.geekh.securitydemo.utils.JwtUtil;
import net.geekh.securitydemo.utils.RedisCache;
import net.geekh.securitydemo.vo.ResponseVo;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/13 08:14
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseVo login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }

        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getXxuser().getId().toString();
//        String jwt = JwtUtil.createJWT(userId);
// TODO jwt报错
        HashMap<String, String> map = new HashMap<>();
        map.put("token", userId);
        redisCache.setCacheObject("login:" + userId, loginUser);
        return new ResponseVo(200, "登陆成功", map);
    }
}
