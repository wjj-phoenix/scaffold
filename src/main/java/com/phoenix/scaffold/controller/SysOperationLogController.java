package com.phoenix.scaffold.controller;

import com.mybatisflex.core.paginate.Page;
import com.phoenix.scaffold.entity.SysOperationLog;
import com.phoenix.scaffold.service.ISysOperationLogService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户操作日志表 控制层。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T00:05:20.381686800
 */
@RestController
@RequestMapping("/sysOperationLog")
public class SysOperationLogController {

    @Resource
    private ISysOperationLogService iSysOperationLogService;

    /**
     * 添加用户操作日志表。
     *
     * @param sysOperationLog 用户操作日志表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SysOperationLog sysOperationLog) {
        return iSysOperationLogService.save(sysOperationLog);
    }

    /**
     * 根据主键删除用户操作日志表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return iSysOperationLogService.removeById(id);
    }

    /**
     * 根据主键更新用户操作日志表。
     *
     * @param sysOperationLog 用户操作日志表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SysOperationLog sysOperationLog) {
        return iSysOperationLogService.updateById(sysOperationLog);
    }

    /**
     * 查询所有用户操作日志表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SysOperationLog> list() {
        return iSysOperationLogService.list();
    }

    /**
     * 根据用户操作日志表主键获取详细信息。
     *
     * @param id 用户操作日志表主键
     * @return 用户操作日志表详情
     */
    @GetMapping("getInfo/{id}")
    public SysOperationLog getInfo(@PathVariable String id) {
        return iSysOperationLogService.getById(id);
    }

    /**
     * 分页查询用户操作日志表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SysOperationLog> page(Page<SysOperationLog> page) {
        return iSysOperationLogService.page(page);
    }

}
