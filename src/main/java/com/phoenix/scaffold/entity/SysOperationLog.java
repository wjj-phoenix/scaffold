package com.phoenix.scaffold.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户操作日志表 实体类。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T00:05:20.381686800
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sys_operation_log")
public class SysOperationLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 物理主键
     */
    @Id
    private String uuid;

    /**
     * 操作描述
     */
    private String operation;

    /**
     * 请求URI
     */
    private String uri;

    /**
     * 请求方法
     */
    private String reqMethod;

    /**
     * 操作方法名
     */
    private String methodName;

    /**
     * 参数内容
     */
    private String params;

    /**
     * 耗时
     */
    private Long respTime;

    /**
     * 客户端IP
     */
    private String clientAddr;

    /**
     * 中转IP
     */
    private String remoteAddr;

    /**
     * 请求结果
     */
    private Integer result;

    /**
     * 失败原因
     */
    private String reason;

    /**
     * 操作人
     */
    private Long operator;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime operatedTime;

}
