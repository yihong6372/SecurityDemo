package net.geekh.securitydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import net.geekh.securitydemo.domain.ErrorLog;
import net.geekh.securitydemo.mapper.ErrorLogMapper;
import net.geekh.securitydemo.service.ErrorLogService;
import net.geekh.securitydemo.utils.IpUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/20 12:23
 */
@Service
public class ErrorLogServiceImpl extends ServiceImpl<ErrorLogMapper, ErrorLog> implements ErrorLogService {

    public void saveLog(Exception e) {
        saveLog(e, e.getMessage());
    }

    @Override
    public void saveLog(Exception e, String msg) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        ErrorLog errorLog = new ErrorLog();
        errorLog.setIp(IpUtils.getIpAddr(request));
        errorLog.setUrl(request.getRequestURI());
        errorLog.setMethod(request.getMethod());


        errorLog.setContent(msg);
        errorLog.setLogTime(String.valueOf(System.currentTimeMillis()));

        this.save(errorLog);
    }
}
