package com.liaoyb.liteshop.common.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.util.Streamable;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页结果（转换Page对象）
 *
 * @param <T>
 * @author liaoyanbo
 */
public class PageResult<T> extends Page<T> implements Streamable<T> {
    public PageResult() {
        super();
    }

    public PageResult(long total, long size, long current, List<T> records) {
        this.setTotal(total);
        this.setSize(size);
        this.setCurrent(current);
        this.setRecords(records);
    }

    @Override
    public Iterator<T> iterator() {
        return this.getRecords().iterator();
    }

    public static <T> PageResult<T> of(IPage<T> page) {
        return new PageResult<T>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords());
    }

    @Override
    public <U> PageResult<U> map(Function<? super T, ? extends U> converter) {
        return new PageResult<U>(this.getTotal(), this.getSize(), this.getCurrent(), this.getConvertedContent(converter));
    }

    protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
        Assert.notNull(converter, "Function must not be null!");
        return this.stream().map(converter::apply).collect(Collectors.toList());
    }

    public PageMeta toPageMeta() {
        PageMeta pageMeta = new PageMeta();
        com.liaoyb.liteshop.common.domain.Page page = new com.liaoyb.liteshop.common.domain.Page();
        page.setPageSize(this.getSize());
        page.setPage(this.getCurrent());
        page.calculate((int) this.getTotal());
        pageMeta.setPagination(page);
        return pageMeta;
    }
}
