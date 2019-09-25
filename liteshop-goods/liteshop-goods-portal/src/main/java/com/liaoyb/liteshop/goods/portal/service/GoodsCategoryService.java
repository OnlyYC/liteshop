package com.liaoyb.liteshop.goods.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoyb.liteshop.goods.portal.model.GoodsCategoryTreeDto;
import com.liaoyb.liteshop.goods.portal.entity.GoodsCategory;

import java.util.List;

/**
 * 商品分类接口
 *
 * @author liaoyb
 */
public interface GoodsCategoryService extends IService<GoodsCategory> {
    /**
     * 获取商品分类树
     *
     * @return 商品分类树
     */
    List<GoodsCategoryTreeDto> getGoodsCategoryTree();
}
