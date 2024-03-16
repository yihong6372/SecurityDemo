package net.geekh.securitydemo.handler;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.geekh.securitydemo.utils.WebUtils;
import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/15 09:27
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseVo vo = new ResponseVo<>(HttpStatus.UNAUTHORIZED.value(), "认证异常" + authException.getMessage());
        String jsonString = JSON.toJSONString(vo);
        String message = authException.getMessage();
        log.info("----->{}",message);
        WebUtils.renderString(response, jsonString);
    }
}
