package com.liaoyb.liteshop.goods.portal.model;

import lombok.Data;

/**
 * 商品搜索参数
 *
 * @author liaoyb
 */
@Data
public class GoodsSearchDto {
    /**
     * 关键词(商品名or描述)
     */
    private String keyword;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 排序，目前只支持price
     */
    private String sort;
    /**
     * 排序方向(asc、desc)
     */
    private String direction;
}
