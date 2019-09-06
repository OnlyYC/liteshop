package com.liaoyb.liteshop.goods.portal.dto;

import lombok.Data;

import java.util.List;

/**
 * 商品分类树
 *
 * @author liaoyb
 */
@Data
public class GoodsCategoryTreeDto {
    private Integer id;
    /**
     * 上级分类的编号：0表示一级分类
     */
    private Integer parentId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类级别：1->1级；2->2级
     */
    private Integer level;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 描述
     */
    private String description;
    /**
     * 子分类
     */
    private List<GoodsCategoryTreeDto> children;
}
