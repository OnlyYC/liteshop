package com.liaoyb.liteshop.goods.portal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liaoyb.liteshop.common.domain.AbstractAuditingEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品分类
 *
 * @author liaoyb
 */
@TableName("liteshop_goods_category")
@Data
public class GoodsCategory  extends AbstractAuditingEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
}
