package net.geekh.securitydemo.controller;

import net.geekh.securitydemo.domain.OpLog;
import net.geekh.securitydemo.service.OpLogService;
import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/25 20:33
 */
@RestController
@RequestMapping("/opLog")
public class OpLogController {


    @Autowired
    private OpLogService opLogService;

    @RequestMapping()
    public ResponseVo getLogList() {
        List<OpLog> opLogList = opLogService.getOpLogList();
        return new ResponseVo(200, opLogList);
    }

    @RequestMapping("/login")
    public ResponseVo getLoginLogList() {
        List<OpLog> loginLogList = opLogService.getLoginLogList();
        return new ResponseVo<>(200, loginLogList);
    }
}
