package net.geekh.securitydemo.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.geekh.securitydemo.domain.OpLog;
import net.geekh.securitydemo.service.OpLogService;
import net.geekh.securitydemo.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/25 13:04
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {


    @Autowired
    private OpLogService opLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("_startTime", System.currentTimeMillis());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Long endTime = System.currentTimeMillis();
        Long startTime = (Long) request.getAttribute("_startTime");
        Long diff = (endTime - startTime);

        OpLog opLog = new OpLog();
        opLog.setReIp(IpUtils.getIpAddr(request));
        opLog.setReMethod(request.getMethod());
        opLog.setReUrl(request.getRequestURI());
        opLog.setReUa(request.getHeader(HttpHeaders.USER_AGENT));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        opLog.setReTime(formatter.format(new Date()));
        opLog.setAccessTime(String.valueOf(diff));
        opLogService.save(opLog);
    }
}
