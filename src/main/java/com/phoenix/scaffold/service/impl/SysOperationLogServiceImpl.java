package com.phoenix.scaffold.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.phoenix.scaffold.entity.SysOperationLog;
import com.phoenix.scaffold.mapper.SysOperationLogMapper;
import com.phoenix.scaffold.service.ISysOperationLogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 用户操作日志表 服务层实现。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T00:05:20.381686800
 */
@Log4j2
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements ISysOperationLogService {
    @Async
    @Override
    public void addSysOperationLog(SysOperationLog sysOperationLog) {
        this.save(sysOperationLog);
    }

}
