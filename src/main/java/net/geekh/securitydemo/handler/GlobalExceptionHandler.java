package net.geekh.securitydemo.handler;

import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/15 13:39
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseBody
    public ResponseVo handleRuntimeException(InternalAuthenticationServiceException e) {
        System.out.println("GlobalExceptionHandler========"+e.toString() );
        return new ResponseVo(-1, "GlobalExceptionHandler========"+e.toString());
    }
}
