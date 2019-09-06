package com.liaoyb.liteshop.goods.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoyb.liteshop.common.domain.Page;
import com.liaoyb.liteshop.common.domain.PageResult;
import com.liaoyb.liteshop.goods.portal.dto.GoodsInfoDto;
import com.liaoyb.liteshop.goods.portal.dto.GoodsSearchDto;
import com.liaoyb.liteshop.goods.portal.dto.GoodsSearchResultDto;
import com.liaoyb.liteshop.goods.portal.entity.Goods;
import com.liaoyb.liteshop.goods.portal.mapper.GoodsMapper;
import com.liaoyb.liteshop.goods.portal.service.GoodsServce;
import org.apache.commons.lang.StringUtils;
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
    public PageResult<GoodsSearchResultDto> search(GoodsSearchDto search, Page page) {
        //todo 根据分类id搜索
        String sortColumn = Optional.ofNullable(search.getSort())
                .map(sort ->sort.startsWith("_") ? sort.substring(1) : sort)
                .orElse(null);
        boolean isAsc = Optional.ofNullable(search.getSort())
                .map(sort -> !sort.startsWith("_"))
                .orElse(true);

        QueryWrapper<Goods> queryWrapper = Wrappers.<Goods>query()
                .eq(search.getCategoryId() != null, "goods_category_id", search.getCategoryId())
                .and(StringUtils.isNotBlank(search.getQ()),
                        i -> i.or(ii -> ii.like("name", search.getQ()))
                                .or(ii2 -> ii2.like("description", search.getQ())))
                .orderBy(StringUtils.isNotBlank(search.getSort()), isAsc, sortColumn);

        IPage<Goods> goodsPage = this.page(page.toPage(), queryWrapper);
        return PageResult.of(goodsPage).map(GoodsSearchResultDto::of);
    }

    @Override
    public GoodsInfoDto getGoodsInfo(long goodsId) {
        Goods goods = this.getById(goodsId);
        return GoodsInfoDto.of(goods);
    }
}
