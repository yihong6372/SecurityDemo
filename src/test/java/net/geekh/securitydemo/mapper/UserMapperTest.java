package net.geekh.securitydemo.mapper;

import lombok.extern.slf4j.Slf4j;
import net.geekh.securitydemo.SecurityDemoApplicationTests;
import net.geekh.securitydemo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/12 23:57
 */
@Slf4j
public class UserMapperTest extends SecurityDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        log.info("user==>{}",users);
    }
}