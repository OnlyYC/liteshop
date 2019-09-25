package com.liaoyb.liteshop.goods.portal.model;

import com.liaoyb.liteshop.goods.portal.entity.Goods;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * 商品详情
 *
 * @author liaoyb
 */
@Data
public class GoodsInfoDto {
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
     * 市场价
     */
    private BigDecimal originalPrice;
    /**
     * 货号
     */
    private String goodsSn;
    /**
     * 单位
     */
    private String unit;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 画册图片，连产品图片限制为5张，以逗号分割
     */
    private String albumPictures;
    /**
     * 产品详情网页内容
     */
    private String detailHtml;
    /**
     * 移动端网页详情
     */
    private String detailMobileHtml;
    /**
     * 商品分类id
     */
    private Integer goodsCategoryId;
    /**
     * 商品分类名称
     */
    private String goodsCategoryName;

    public static GoodsInfoDto of(Goods goods) {
        if (goods == null) {
            return null;
        }
        GoodsInfoDto goodsInfoDto = new GoodsInfoDto();
        BeanUtils.copyProperties(goods, goodsInfoDto);
        return goodsInfoDto;
    }
}
