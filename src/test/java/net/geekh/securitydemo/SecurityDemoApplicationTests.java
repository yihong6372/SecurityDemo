package net.geekh.securitydemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
@Slf4j
public class SecurityDemoApplicationTests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    public void testBCryptPasswordEncoder() {
        String encode1 = passwordEncoder.encode("112233");
        String encode2 = passwordEncoder.encode("1111");


        log.info("encoder1=>>{}", encode1);
        log.info("encoder2=>>{}", encode2);
        boolean matches = passwordEncoder.matches("112233", "$2a$10$JMLd6GX17Kp.FeFF6VWRJuiuKH9P0KA3uvLRWorKsOcMTGu8WLy7O");
        log.info("matches={}",matches);
    }

}
