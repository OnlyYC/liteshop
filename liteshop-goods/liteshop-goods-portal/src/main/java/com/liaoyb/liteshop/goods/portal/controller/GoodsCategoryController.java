package com.liaoyb.liteshop.goods.portal.controller;

import com.liaoyb.liteshop.common.domain.EmptyMeta;
import com.liaoyb.liteshop.common.domain.Response;
import com.liaoyb.liteshop.goods.portal.model.GoodsCategoryTreeDto;
import com.liaoyb.liteshop.goods.portal.service.GoodsCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类控制层
 *
 * @author liaoyb
 */
@Slf4j
@RestController
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 获取商品分类树
     *
     * @return 商品分类树
     */
    @GetMapping("/goods-category/tree")
    public Response<List<GoodsCategoryTreeDto>, EmptyMeta> getGoodsCategoryTree() {
        List<GoodsCategoryTreeDto> goodsCategoryTreeDtoList = goodsCategoryService.getGoodsCategoryTree();
        return Response.success(goodsCategoryTreeDtoList);
    }
}
