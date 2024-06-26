package com.phoenix.scaffold.model.vo;

import com.phoenix.scaffold.model.SaveValue;
import com.phoenix.scaffold.model.UpdateValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineVO {
    /**
     * 机器名
     */
    @NotBlank(message = "机器名不能为空", groups = {SaveValue.class, UpdateValue.class})
    private String name;

    /**
     * 机器IP
     */
    @NotBlank(message = "机器IP不能为空", groups = {SaveValue.class})
    private String ip;

    /**
     * 用于SSH链接的端口
     */
    @NotNull(message = "SSH连接端口不能为空", groups = {SaveValue.class})
    private Integer port;

    /**
     * 主机操作系统
     */
    private String operatingSystem;

    /**
     * 是否为虚拟机（0：不是；1：是）
     */
    @NotNull(message = "需要指定机器是否为虚拟机", groups = {SaveValue.class, UpdateValue.class})
    private Boolean virtual;

    /**
     * 是否可用（0：不可用；1：可用）
     */
    @NotNull(message = "需要指定机器是否可用", groups = {SaveValue.class, UpdateValue.class})
    private Boolean enabled;

    /**
     * 备注信息
     */
    private String remark;

    @Valid
    private List<ResourceAuthVO> authVOS;
}
