package com.liaoyb.liteshop.goods.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoyb.liteshop.common.domain.Page;
import com.liaoyb.liteshop.common.domain.PageResult;
import com.liaoyb.liteshop.goods.portal.model.GoodsInfoDto;
import com.liaoyb.liteshop.goods.portal.model.GoodsSearchVo;
import com.liaoyb.liteshop.goods.portal.model.GoodsSearchResultDto;
import com.liaoyb.liteshop.goods.portal.entity.Goods;

/**
 * 商品服务接口
 *
 * @author liaoyb
 */
public interface GoodsServce extends IService<Goods> {
    /**
     * 商品搜索
     *
     * @param search 搜索参数
     * @param page   分页参数
     * @return 商品搜索的分页结果
     */
    PageResult<GoodsSearchResultDto> search(GoodsSearchVo search, Page page);

    /**
     * 获取商品详情
     *
     * @param goodsId 商品id
     * @return 商品详情
     */
    GoodsInfoDto getGoodsInfo(long goodsId);
}
