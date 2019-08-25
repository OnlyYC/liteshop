package com.liaoyb.liteshop.goods.portal;

import com.liaoyb.liteshop.goods.portal.entity.Goods;
import com.liaoyb.liteshop.goods.portal.mapper.GoodsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author liaoyb
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void test(){
        List<Goods> goodsList = goodsMapper.selectList(null);
        System.out.println(goodsList);
    }
}
