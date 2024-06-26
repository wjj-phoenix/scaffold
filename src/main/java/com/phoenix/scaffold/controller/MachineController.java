package com.phoenix.scaffold.controller;

import com.phoenix.scaffold.entity.Machine;
import com.phoenix.scaffold.lang.IPage;
import com.phoenix.scaffold.model.SaveValue;
import com.phoenix.scaffold.model.UpdateValue;
import com.phoenix.scaffold.model.vo.MachineVO;
import com.phoenix.scaffold.service.IMachineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 主机表 控制层。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T14:30:04.984061600
 */
@RestController
@RequestMapping("/machine")
@Tag(name = "机器管理接口", description = "用户管理机器")
public class MachineController {
    @Resource
    private IMachineService service;

    /**
     * 添加主机表。
     *
     * @param machine 主机表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping()
    @Operation(summary = "新增机器接口")
    public Long save(@Validated(SaveValue.class) @RequestBody MachineVO machine) {
        return service.addMachine(machine);
    }

    /**
     * 根据主键删除主机表。
     *
     * @param ids 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping()
    @Operation(summary = "批量删除机器接口")
    public boolean remove(@RequestBody Set<Long> ids) {
        return service.delMachine(ids);
    }

    /**
     * 根据主键更新主机表。
     *
     * @param machine 主机表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/{id}")
    @Operation(summary = "根据主键ID修改机器接口")
    public boolean update(@PathVariable Long id, @Validated(UpdateValue.class) @RequestBody MachineVO machine) {
        return service.updateMachineById(id, machine);
    }

    /**
     * 查询所有主机表。
     *
     * @return 所有数据
     */
    @GetMapping()
    @Operation(summary = "查询所有机器接口")
    public List<Machine> list() {
        return service.fetchAllMachines();
    }

    /**
     * 根据主机表主键获取详细信息。
     *
     * @param id 主机表主键
     * @return 主机表详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据主键ID查询机器详细信息接口")
    public Machine getInfo(@PathVariable Long id) {
        return service.fetchMachineById(id);
    }

    /**
     * 分页查询主机表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/pages")
    @Operation(summary = "分页查询机器接口")
    public IPage<Machine> page(@RequestParam(defaultValue = "1", required = false) Integer page,
                               @RequestParam(defaultValue = "20", required = false) Integer limit,
                               @RequestParam(defaultValue = "", required = false) String condition) {
        return service.fetchMachinesByCondition(page, limit, condition);
    }

}
