package net.geekh.securitydemo.handler;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.geekh.securitydemo.domain.ErrorLog;
import net.geekh.securitydemo.service.ErrorLogService;
import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/15 13:39
 */
@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private ErrorLogService errorLogService;


    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseBody
    public ResponseVo handleRuntimeException(InternalAuthenticationServiceException e) {
        System.out.println("GlobalExceptionHandler======"+e.getMessage() );
        return new ResponseVo(-1, "GlobalExceptionHandler"+e.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseVo handleRuntimeException(Exception e) {

        errorLogService.saveLog(e);
        System.out.println("RuntimeException========"+e.getMessage() );
        return new ResponseVo(-111, "RuntimeException="+e.toString());
    }

}
