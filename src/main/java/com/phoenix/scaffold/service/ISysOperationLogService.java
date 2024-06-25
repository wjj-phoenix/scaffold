package com.phoenix.scaffold.service;

import com.mybatisflex.core.service.IService;
import com.phoenix.scaffold.entity.SysOperationLog;

/**
 * 用户操作日志表 服务层。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T00:05:20.381686800
 */
public interface ISysOperationLogService extends IService<SysOperationLog> {
    void addSysOperationLog(SysOperationLog sysOperationLog);
}
