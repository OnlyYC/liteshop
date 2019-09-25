package com.liaoyb.liteshop.goods.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoyb.liteshop.common.consts.CommonConsts;
import com.liaoyb.liteshop.common.domain.Page;
import com.liaoyb.liteshop.common.domain.PageResult;
import com.liaoyb.liteshop.goods.portal.model.GoodsInfoDto;
import com.liaoyb.liteshop.goods.portal.model.GoodsSearchDto;
import com.liaoyb.liteshop.goods.portal.model.GoodsSearchResultDto;
import com.liaoyb.liteshop.goods.portal.model.GoodsSearchVo;
import com.liaoyb.liteshop.goods.portal.entity.Goods;
import com.liaoyb.liteshop.goods.portal.mapper.GoodsMapper;
import com.liaoyb.liteshop.goods.portal.service.GoodsServce;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 商品服务实现
 *
 * @author liaoyb
 */
@Service
public class GoodsServceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsServce {

    @Override
    public PageResult<GoodsSearchResultDto> search(GoodsSearchVo search, Page page) {
        //todo 根据分类id搜索
        String sortColumn = Optional.ofNullable(search.getSort())
                .map(sort -> sort.startsWith("_") ? sort.substring(1) : sort)
                .orElse(null);
        String sortDirection = Optional.ofNullable(search.getSort())
                .map(sort -> !sort.startsWith("_"))
                .orElse(true) ? CommonConsts.SORT_ASC : CommonConsts.SORT_DESC;

        //转换查询参数
        GoodsSearchDto goodsSearchDto = new GoodsSearchDto();
        goodsSearchDto.setCategoryId(search.getCategoryId());
        goodsSearchDto.setKeyword(search.getQ());
        goodsSearchDto.setSort(sortColumn);
        goodsSearchDto.setDirection(sortDirection);

        IPage<Goods> goodsPage = getBaseMapper().search(page.toPage(), goodsSearchDto);
        return PageResult.of(goodsPage).map(GoodsSearchResultDto::of);
    }

    @Override
    public GoodsInfoDto getGoodsInfo(long goodsId) {
        Goods goods = this.getById(goodsId);
        return GoodsInfoDto.of(goods);
    }
}
