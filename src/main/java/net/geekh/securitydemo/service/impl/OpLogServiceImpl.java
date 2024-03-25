package net.geekh.securitydemo.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.geekh.securitydemo.domain.OpLog;
import net.geekh.securitydemo.mapper.OpLogMapper;
import net.geekh.securitydemo.service.OpLogService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OpLogServiceImpl extends ServiceImpl<OpLogMapper,OpLog> implements OpLogService {


    @Override
    public List<OpLog> getLoginLogList() {
        QueryWrapper<OpLog> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("re_url", "/user/login");
        queryWrapper.orderBy(true, false, "re_time");
        queryWrapper.last("limit 1000");
        return this.getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public List<OpLog> getOpLogList() {
        return this.lambdaQuery()
                .orderBy(true, false, OpLog::getReTime)
                .last("limit 1000")
                .list();
    }
}
