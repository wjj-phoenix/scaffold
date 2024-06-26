package com.phoenix.scaffold.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.phoenix.scaffold.common.SelectCommon;
import com.phoenix.scaffold.entity.ResourceAuth;
import com.phoenix.scaffold.lang.IPage;
import com.phoenix.scaffold.mapper.ResourceAuthMapper;
import com.phoenix.scaffold.model.vo.ResourceAuthVO;
import com.phoenix.scaffold.service.IResourceAuthService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.phoenix.scaffold.entity.table.ResourceAuthTableDef.RESOURCE_AUTH;

/**
 * 主机账号 服务层实现。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T14:30:04.984061600
 */
@Service
public class ResourceAuthServiceImpl extends ServiceImpl<ResourceAuthMapper, ResourceAuth> implements IResourceAuthService {
    @Override
    public IPage<ResourceAuth> fetchResourceAuthsByCondition(Integer page, Integer limit, String condition) {
        return new IPage<>(new SelectCommon<ResourceAuth>().findAll(page, limit, condition, this));
    }

    @Override
    public ResourceAuth fetchResourceAuthById(long userId) {
        return this.getById(userId);
    }

    @Override
    public List<ResourceAuth> fetchAllResourceAuths() {
        return this.list(QueryWrapper.create().select(RESOURCE_AUTH.ALL_COLUMNS).from(RESOURCE_AUTH));
    }

    @Override
    public List<ResourceAuth> fetchAllResourceAuthsByMachineId(Long machineId) {
        return this.list(
                QueryWrapper.create()
                        .select(RESOURCE_AUTH.ALL_COLUMNS).from(RESOURCE_AUTH)
                        .where(RESOURCE_AUTH.MACHINE_ID.eq(machineId))
        );
    }

    @Override
    public Long addResourceAuth(ResourceAuthVO ResourceAuthVO) {
        ResourceAuth ResourceAuth = BeanUtil.toBean(ResourceAuthVO, ResourceAuth.class);
        if (!this.save(ResourceAuth)) {
            throw new IllegalArgumentException("添加主机账号失败");
        }
        return ResourceAuth.getId();
    }

    @Override
    public Boolean updateResourceAuthById(Long id, ResourceAuthVO ResourceAuthVO) {
        ResourceAuth ResourceAuth = BeanUtil.toBean(ResourceAuthVO, ResourceAuth.class);
        ResourceAuth.setId(id);
        if (!this.updateById(ResourceAuth)) {
            throw new IllegalArgumentException("添加主机账号失败");
        }
        return true;
    }

    @Override
    public Boolean delResourceAuth(Set<Long> ids) {
        if (!this.removeByIds(ids)) {
            throw new IllegalArgumentException("删除主机账户失败!");
        }
        return true;
    }
}
