package com.phoenix.scaffold.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import com.phoenix.scaffold.entity.ResourceAuth;

/**
 * 主机账号 映射层。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T14:30:04.984061600
 */
@Mapper
public interface ResourceAuthMapper extends BaseMapper<ResourceAuth> {

}
