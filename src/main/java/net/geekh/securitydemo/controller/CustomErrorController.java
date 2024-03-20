package net.geekh.securitydemo.controller;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/15 13:53
 */
@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public ResponseVo handleError(HttpServletRequest request) throws ServletException, IOException {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return new ResponseVo<>(HttpStatus.NOT_FOUND.value(),"页面未找到 - 404");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return new ResponseVo<>( HttpStatus.FORBIDDEN.value(), "没有权限访问该资源 - 403");
            }
            // 可以添加更多的错误处理
        }
        return new ResponseVo<>( HttpStatus.INTERNAL_SERVER_ERROR.value(),"错误: ");
    }

}
