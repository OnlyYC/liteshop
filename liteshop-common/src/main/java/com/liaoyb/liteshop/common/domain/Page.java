package com.liaoyb.liteshop.common.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页类
 *
 * @author liaoyb
 */
@Data
public final class Page implements Serializable {
    /**
     * 每页显示数量
     */
    private long pageSize;
    /**
     * 当前页码
     */
    private long page;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 当前页数量
     */
    private long count;

    /**
     * 默认无参构造器，初始化各值
     */
    public Page() {
        this.pageSize = 20;
        this.page = 1;
        this.pages = 0;
        this.total = 0;
        this.count = 0;
    }

    public void calculate(long total) {
        this.setTotal(total);
        this.pages = (total / pageSize) + ((total % pageSize) > 0 ? 1 : 0);
    }

    /**
     * 获取分页起始位置和偏移量
     *
     * @return 分页起始位置和偏移量数组
     */
    public long[] paginate() {
        // 数量为零时,直接从0开始
        return new long[]{total > 0 ? (page - 1) * pageSize : 0, pageSize};
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize <= 0 ? 20 : pageSize;
    }

    public void setPage(long page) {
        this.page = page <= 0 ? 1 : page;
    }

    public <T> IPage<T> toPage() {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(this.page, this.pageSize);
        return page;
    }
}
