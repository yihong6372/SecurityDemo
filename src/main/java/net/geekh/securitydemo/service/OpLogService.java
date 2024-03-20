package net.geekh.securitydemo.service;



import com.baomidou.mybatisplus.extension.service.IService;
import net.geekh.securitydemo.domain.OpLog;

import java.util.List;

public interface OpLogService extends IService<OpLog> {

    List<OpLog> getLoginLogList();
}
