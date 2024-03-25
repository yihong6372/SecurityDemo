package net.geekh.securitydemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.geekh.securitydemo.SecurityDemoApplicationTests;
import net.geekh.securitydemo.domain.OpLog;
import net.geekh.securitydemo.service.ErrorLogService;
import net.geekh.securitydemo.service.OpLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/25 16:53
 */
@Slf4j
class OpLogServiceImplTest extends SecurityDemoApplicationTests {

    @Autowired
    private OpLogService opLogService;

    @Test
    void getLoginLogList() {
        List<OpLog> loginLogList = opLogService.getLoginLogList();
        log.info("list{}", loginLogList);
    }


    @Test
    void getOpLogList() {
        List<OpLog> opLogList = opLogService.getOpLogList();
        log.info("opLogList{}", opLogList);
    }
}