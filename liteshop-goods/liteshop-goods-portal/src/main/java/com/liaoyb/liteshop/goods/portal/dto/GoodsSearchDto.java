package com.liaoyb.liteshop.goods.portal.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * 商品搜索参数
 *
 * @author liaoyb
 */
@Data
public class GoodsSearchDto {
    /**
     * 关键词
     */
    private String q;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 排序（default、price、sales），以横线开头表示降序，目前只支持price
     */
    @Pattern(regexp = "price|_price", message = "不支持的排序参数")
    private String sort;
    /**
     * todo 筛选(多个筛选用;分割，其中多个参数用,号分割),如price,30,720;property,-1,17
     */
    private String filter;
}
