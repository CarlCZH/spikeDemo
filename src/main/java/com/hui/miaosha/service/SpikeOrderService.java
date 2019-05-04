package com.hui.miaosha.service;

import com.hui.miaosha.domain.OrderInfo;
import com.hui.miaosha.domain.SpikeOrder;
import com.hui.miaosha.vo.SpikeGoodsVo;

public interface SpikeOrderService {

    /**
     * 获取秒杀订单信息
     */
    SpikeOrder getOrderInfo(Long userId, Long goodsId);

    /**
     * 增加订单信息与秒杀订单信息
     */
    OrderInfo setOrderInfo(Long id, SpikeGoodsVo goodsInfo);


}
