package com.phoenix.scaffold.lang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IPage<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 查询到的总条数
     */
    private long total;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 当前页,PageNum
     */
    private int page;

    /**
     * 每页条数,PageSize
     */
    private int limits;

    /**
     * 分页结果
     */
    private List<T> rows;

    public static <T  extends Serializable> IPage<T> emptyPage() {
        IPage<T> result = new IPage<>();
        result.setRows(new ArrayList<>());
        return result;
    }

    /**
     * 根据完整的项目列表创建假分页。
     *
     * @param list     完整的项目列表，用于分页。
     * @param pageNum  要检索的页码。
     * @param pageSize  每页的项目数目。
     * @return 返回指定页面的Page对象，包含对应的项目列表。
     */
    public static <T extends Serializable> IPage<T> startPage(List<T> list, int pageNum, int pageSize) {
        int totalItems = list.size();
        int totalPages = (int)Math.ceil((double)pageNum / pageSize);

        // 计算请求页面上第一个项目的索引
        int startIndex = (pageNum - 1) * pageSize;
        // 确保不会超出列表范围
        startIndex = Math.max(startIndex, 0);

        // 计算请求页面上最后一个项目的索引
        int endIndex = startIndex + pageSize;
        // 确保不会超出列表范围
        endIndex = Math.min(endIndex, totalItems);

        // 从完整列表中提取当前页面的子列表
        List<T> pageItems = list.subList(startIndex, endIndex);

        // 创建并填充Page对象
        IPage<T> page = new IPage<>();
        page.setPage(pageNum);
        page.setLimits(pageSize);
        page.setTotal(totalItems);
        page.setPages(totalPages);
        page.setRows(pageItems);
        return page;
    }


}
