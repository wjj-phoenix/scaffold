package com.phoenix.scaffold.controller;

import com.phoenix.scaffold.entity.ResourceAuth;
import com.phoenix.scaffold.lang.IPage;
import com.phoenix.scaffold.model.vo.ResourceAuthVO;
import com.phoenix.scaffold.service.IResourceAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 主机账号 控制层。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T14:30:04.984061600
 */
@RestController
@RequestMapping("/resource-auth")
@Tag(name = "授权凭证接口", description = "用户管理主机的授权凭证")
public class ResourceAuthController {
    @Resource
    private IResourceAuthService service;

    /**
     * 添加主机账号。
     *
     * @param hostUser 主机账号
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping()
    public Long save(@RequestBody ResourceAuthVO hostUser) {
        return service.addResourceAuth(hostUser);
    }

    /**
     * 根据主键删除主机账号。
     *
     * @param ids 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping()
    public boolean remove(@RequestBody Set<Long> ids) {
        return service.delResourceAuth(ids);
    }

    /**
     * 根据主键更新主机账号。
     *
     * @param hostUser 主机账号
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody ResourceAuthVO hostUser) {
        return service.updateResourceAuthById(id, hostUser);
    }

    /**
     * 查询所有主机账号。
     *
     * @return 所有数据
     */
    @GetMapping()
    public List<ResourceAuth> list() {
        return service.fetchAllResourceAuths();
    }

    /**
     * 根据主机ID查询所有主机账号。
     *
     * @param machineId 主机ID
     * @return 所有数据
     */
    @GetMapping("/machine/{machineId}")
    public List<ResourceAuth> fetchByMachineId(@PathVariable Long machineId) {
        return service.fetchAllResourceAuthsByMachineId(machineId);
    }

    /**
     * 根据主机账号主键获取详细信息。
     *
     * @param id 主机账号主键
     * @return 主机账号详情
     */
    @GetMapping("/{id}")
    public ResourceAuth getInfo(@PathVariable Long id) {
        return service.fetchResourceAuthById(id);
    }

    /**
     * 分页查询主机账号。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public IPage<ResourceAuth> page(@RequestParam(defaultValue = "1", required = false) Integer page,
                                    @RequestParam(defaultValue = "20", required = false) Integer limit,
                                    @RequestParam(defaultValue = "", required = false) String condition) {
        return service.fetchResourceAuthsByCondition(page, limit, condition);
    }
}
