package net.geekh.securitydemo.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.mapper.UserMapper;
import net.geekh.securitydemo.service.user.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/17 13:05
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserMapper, User> implements UserInfoService {
}
