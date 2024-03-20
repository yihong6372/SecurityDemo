package net.geekh.securitydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import net.geekh.securitydemo.domain.ErrorLog;
import net.geekh.securitydemo.domain.OpLog;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/20 12:16
 */
public interface OpLogMapper extends BaseMapper<OpLog> {
}
