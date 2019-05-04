package com.hui.miaosha.vo;

import com.hui.miaosha.domain.OrderInfo;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 21:59 2019\5\3 0003
 */
public class OrderDetailVo {

    private SpikeGoodsVo goodsInfo;
    private OrderInfo orderInfo;

    public SpikeGoodsVo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(SpikeGoodsVo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
