package com.phoenix.scaffold.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 主机账号 实体类。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T14:39:27.639712500
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("resource_auth")
public class ResourceAuth implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否为特权账号
     */
    private Boolean privileged;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 所属机器ID
     */
    private Long machineId;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 上次连接时间
     */
    private LocalDateTime latestConnTime;

    /**
     * 有效结束时间
     */
    private LocalDateTime effectiveEndTime;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private LocalDateTime createdTime;

    /**
     * 创建用户
     */
    private Long createdUser;

}
