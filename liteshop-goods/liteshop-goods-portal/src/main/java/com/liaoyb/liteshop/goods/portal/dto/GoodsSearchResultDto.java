package com.liaoyb.liteshop.goods.portal.dto;

import com.liaoyb.liteshop.goods.portal.entity.Goods;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品搜索结果dto
 *
 * @author liaoyb
 */
@Data
public class GoodsSearchResultDto {
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品主图
     */
    private String picture;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * todo 销量
     */
    private long salesVolume;

    public static GoodsSearchResultDto of(Goods goods) {
        GoodsSearchResultDto goodsSearchResultDto = new GoodsSearchResultDto();
        goodsSearchResultDto.setId(goods.getId());
        goodsSearchResultDto.setName(goods.getName());
        goodsSearchResultDto.setPicture(goods.getPicture());
        goodsSearchResultDto.setPrice(goods.getPrice());
        goodsSearchResultDto.setSalesVolume(0);
        return goodsSearchResultDto;
    }
}
