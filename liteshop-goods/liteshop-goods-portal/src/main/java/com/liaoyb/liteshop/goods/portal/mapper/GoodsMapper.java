package com.liaoyb.liteshop.goods.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liaoyb.liteshop.goods.portal.model.GoodsSearchDto;
import com.liaoyb.liteshop.goods.portal.entity.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * 商品Mapper
 *
 * @author liaoyb
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 商品搜索
     *
     * @param page           分页参数
     * @param goodsSearchDto 搜索参数
     * @return 商品分页
     */
    IPage<Goods> search(Page page, @Param("goodsSearch") GoodsSearchDto goodsSearchDto);
}
