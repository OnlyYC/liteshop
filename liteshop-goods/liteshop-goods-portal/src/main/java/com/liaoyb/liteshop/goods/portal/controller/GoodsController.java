package com.liaoyb.liteshop.goods.portal.controller;

import com.liaoyb.liteshop.common.domain.EmptyMeta;
import com.liaoyb.liteshop.common.domain.Page;
import com.liaoyb.liteshop.common.domain.PageMeta;
import com.liaoyb.liteshop.common.domain.PageResult;
import com.liaoyb.liteshop.common.domain.Response;
import com.liaoyb.liteshop.common.util.ResponseUtil;
import com.liaoyb.liteshop.goods.portal.dto.GoodsInfoDto;
import com.liaoyb.liteshop.goods.portal.dto.GoodsSearchDto;
import com.liaoyb.liteshop.goods.portal.dto.GoodsSearchResultDto;
import com.liaoyb.liteshop.goods.portal.entity.Goods;
import com.liaoyb.liteshop.goods.portal.service.GoodsServce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * 商品服务控制层
 *
 * @author liaoyb
 */
@Slf4j
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
    public Response<List<GoodsSearchResultDto>, PageMeta> search(@Validated GoodsSearchDto search, Page page) {
        log.info("商品搜索：{},{}", search, page);
        PageResult<GoodsSearchResultDto> goodsPage = goodsServce.search(search, page);
        return Response.success(goodsPage.getRecords(), goodsPage.toPageMeta());
    }

    /**
     * 获取商品详情
     *
     * @param goodsId 商品id
     * @return 商品详情
     */
    @GetMapping("/goods/{goodsId:\\d+}")
    public Response<GoodsInfoDto, EmptyMeta> getGoodsInfo(@Min(1) @PathVariable("goodsId") long goodsId) {
        log.info("获取商品详情：{}", goodsId);
        GoodsInfoDto goodsInfo = goodsServce.getGoodsInfo(goodsId);
        return ResponseUtil.wrapOrNotFound(goodsInfo);
    }




}
