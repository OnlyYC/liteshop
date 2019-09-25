package com.liaoyb.liteshop.goods.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoyb.liteshop.goods.portal.model.GoodsCategoryTreeDto;
import com.liaoyb.liteshop.goods.portal.entity.GoodsCategory;
import com.liaoyb.liteshop.goods.portal.mapper.GoodsCategoryMapper;
import com.liaoyb.liteshop.goods.portal.service.GoodsCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商品分类服务接口实现
 *
 * @author liaoyb
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {
    @Override
    public List<GoodsCategoryTreeDto> getGoodsCategoryTree() {
        //获取所有分类
        List<GoodsCategory> all = this.list();

        //转换树形结构
        return convert2Tree(all);
    }

    /**
     * 转换为树形结构
     *
     * @param all 所有分类
     * @return 商品分类树形结构
     */
    private List<GoodsCategoryTreeDto> convert2Tree(List<GoodsCategory> all) {
        List<GoodsCategoryTreeDto> roots = all.stream()
                .filter(goodsCategory -> goodsCategory.getParentId() == 0)
                .map(this::toGoodsCategoryTreeDto)
                .collect(Collectors.toList());

        roots.forEach(goodsCategoryTreeDto -> {
            List<GoodsCategoryTreeDto> children = getChildren(goodsCategoryTreeDto.getId(), all);
            goodsCategoryTreeDto.setChildren(children);
        });
        return roots;
    }

    /**
     * 获取子分类
     *
     * @param parentId 父级分类id
     * @param all      所有分类
     * @return 子分类
     */
    private List<GoodsCategoryTreeDto> getChildren(Integer parentId, List<GoodsCategory> all) {
        List<GoodsCategoryTreeDto> list = all.stream().filter(goodsCategory -> Objects.equals(goodsCategory.getParentId(), parentId))
                .map(this::toGoodsCategoryTreeDto)
                .collect(Collectors.toList());

        list.forEach(goodsCategoryTreeDto -> {
            List<GoodsCategoryTreeDto> children = getChildren(goodsCategoryTreeDto.getId(), all);
            goodsCategoryTreeDto.setChildren(children);
        });
        return list;
    }

    private GoodsCategoryTreeDto toGoodsCategoryTreeDto(GoodsCategory goodsCategory) {
        if (goodsCategory == null) {
            return null;
        }
        GoodsCategoryTreeDto goodsCategoryTreeDto = new GoodsCategoryTreeDto();
        BeanUtils.copyProperties(goodsCategory, goodsCategoryTreeDto);
        return goodsCategoryTreeDto;
    }
}
