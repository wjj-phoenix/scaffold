package com.phoenix.scaffold.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.phoenix.scaffold.model.SaveValue;
import com.phoenix.scaffold.model.UpdateValue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceAuthVO {
    /**
     * 名称
     */
    @NotBlank(message = "认证名称不能为空", groups = {SaveValue.class, UpdateValue.class})
    private String name;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {SaveValue.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {SaveValue.class})
    @Size(min = 6, max = 20, message = "用户密码长度在6-20个字符")
    private String password;

    /**
     * 是否为特权账号
     */
    @NotNull(message = "需要指定用户是否为特权用户", groups = {SaveValue.class, UpdateValue.class})
    private Boolean privileged;

    /**
     * 是否可用
     */
    @NotNull(message = "需要指定账号是否可用", groups = {SaveValue.class, UpdateValue.class})
    private Boolean enabled;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 有效结束时间
     */
    @Future(message = "有效期需要指定为将来时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "有效期不能为空", groups = {SaveValue.class, UpdateValue.class})
    private LocalDateTime effectiveEndTime;
}
