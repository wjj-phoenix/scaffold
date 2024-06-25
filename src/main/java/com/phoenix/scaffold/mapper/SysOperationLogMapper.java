package com.phoenix.scaffold.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import com.phoenix.scaffold.entity.SysOperationLog;

/**
 * 用户操作日志表 映射层。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T00:05:20.381686800
 */
@Mapper
public interface SysOperationLogMapper extends BaseMapper<SysOperationLog> {

}
