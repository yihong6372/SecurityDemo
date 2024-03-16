package net.geekh.securitydemo.mapper;

import lombok.extern.slf4j.Slf4j;
import net.geekh.securitydemo.SecurityDemoApplicationTests;
import net.geekh.securitydemo.domain.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/15 09:10
 */
@Slf4j
public class MenuMapperTest extends SecurityDemoApplicationTests {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void selectPermsByUserId() {
        List<String> list = menuMapper.selectPermsByUserId(2L);
        log.info("list=>{}",list);
    }
}