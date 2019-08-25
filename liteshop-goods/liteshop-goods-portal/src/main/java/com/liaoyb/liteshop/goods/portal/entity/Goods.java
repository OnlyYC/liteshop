package com.liaoyb.liteshop.goods.portal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.liaoyb.liteshop.common.domain.AbstractAuditingEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品
 *
 * @author liaoyb
 */
@TableName("liteshop_goods")
@Data
public class Goods extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 上架状态：1->上架、2->下架
     */
    private Integer publishStatus;
    /**
     * 审核状态：0->未审核；1->审核通过；2->审核不通过
     */
    private Integer verifyStatus;
    /**
     * 库存
     */
    private Integer stock;
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
}
