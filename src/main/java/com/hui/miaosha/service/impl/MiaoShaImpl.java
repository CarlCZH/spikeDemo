package com.hui.miaosha.service.impl;

import com.hui.miaosha.domain.OrderInfo;
import com.hui.miaosha.service.MiaoSha;
import com.hui.miaosha.service.SpikeGoodsService;
import com.hui.miaosha.service.SpikeOrderService;
import com.hui.miaosha.vo.SpikeGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 22:08 2019\4\21 0021
 */
@Service
public class MiaoShaImpl implements MiaoSha {

    @Autowired
    SpikeOrderService spikeOrderService;

    @Autowired
    SpikeGoodsService spikeGoodsService;


    @Override
    @Transactional
    public OrderInfo stockReduce(Long userId, SpikeGoodsVo goodsInfo) {
        spikeGoodsService.stockReduce(goodsInfo);
        return spikeOrderService.setOrderInfo(userId, goodsInfo);
    }
}
