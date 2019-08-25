package com.liaoyb.liteshop.goods.portal.controller;

import com.liaoyb.liteshop.common.domain.Page;
import com.liaoyb.liteshop.common.domain.PageMeta;
import com.liaoyb.liteshop.common.domain.PageResult;
import com.liaoyb.liteshop.common.domain.Response;
import com.liaoyb.liteshop.goods.portal.dto.GoodsSearchDto;
import com.liaoyb.liteshop.goods.portal.entity.Goods;
import com.liaoyb.liteshop.goods.portal.service.GoodsServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品服务控制层
 *
 * @author liaoyb
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsServce goodsServce;

    /**
     *
     * 商品搜索
     *
     * @param search 搜索参数
     * @param page 分页参数
     * @return 商品搜索列表
     */
    @GetMapping("/goods")
    public Response<List<Goods>, PageMeta> search(@Validated GoodsSearchDto search, Page page) {
        PageResult<Goods> goodsPage = goodsServce.search(search, page);
        return Response.success(goodsPage.getRecords(), goodsPage.toPageMeta());
    }


}
