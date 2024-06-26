package com.phoenix.scaffold.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 * 主机账号 表定义层。
 *
 * @author wjj-phoenix
 * @since 2024-06-26T14:39:27.639712500
 */
public class ResourceAuthTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主机账号
     */
    public static final ResourceAuthTableDef RESOURCE_AUTH = new ResourceAuthTableDef();

    /**
     * 主键ID
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 备注信息
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 是否可用
     */
    public final QueryColumn ENABLED = new QueryColumn(this, "enabled");

    /**
     * 密码
     */
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    /**
     * 用户名
     */
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    /**
     * 所属机器ID
     */
    public final QueryColumn MACHINE_ID = new QueryColumn(this, "machine_id");

    /**
     * 是否为特权账号
     */
    public final QueryColumn PRIVILEGED = new QueryColumn(this, "privileged");

    /**
     * 创建时间
     */
    public final QueryColumn CREATED_TIME = new QueryColumn(this, "created_time");

    /**
     * 创建用户
     */
    public final QueryColumn CREATED_USER = new QueryColumn(this, "created_user");

    /**
     * 上次连接时间
     */
    public final QueryColumn LATEST_CONN_TIME = new QueryColumn(this, "latest_conn_time");

    /**
     * 有效结束时间
     */
    public final QueryColumn EFFECTIVE_END_TIME = new QueryColumn(this, "effective_end_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, USERNAME, PASSWORD, PRIVILEGED, ENABLED, MACHINE_ID, REMARK, LATEST_CONN_TIME, EFFECTIVE_END_TIME, CREATED_TIME, CREATED_USER};

    public ResourceAuthTableDef() {
        super("", "resource_auth");
    }

    private ResourceAuthTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ResourceAuthTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ResourceAuthTableDef("", "resource_auth", alias));
    }

}
