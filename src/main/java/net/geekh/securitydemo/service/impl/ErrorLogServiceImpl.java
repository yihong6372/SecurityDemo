package net.geekh.securitydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.geekh.securitydemo.domain.ErrorLog;
import net.geekh.securitydemo.mapper.ErrorLogMapper;
import net.geekh.securitydemo.service.ErrorLogService;
import org.springframework.stereotype.Service;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/20 12:23
 */
@Service
public class ErrorLogServiceImpl extends ServiceImpl<ErrorLogMapper, ErrorLog> implements ErrorLogService {

}
