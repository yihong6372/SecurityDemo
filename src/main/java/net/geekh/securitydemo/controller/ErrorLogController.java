package net.geekh.securitydemo.controller;

import net.geekh.securitydemo.domain.ErrorLog;
import net.geekh.securitydemo.service.ErrorLogService;
import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/25 19:47
 */
@RestController
@RequestMapping("/errorLog")
public class ErrorLogController {

    @Autowired
    private ErrorLogService errorLogService;

    @GetMapping("/list")
    public ResponseVo list() {
        List<ErrorLog> list = errorLogService.list();
        return new ResponseVo<>(200, list);
    }

}
