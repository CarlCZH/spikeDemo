package com.hui.miaosha.domain;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 16:08 2019\4\15 0015
 */
public class SpikeOrder {

    private Long id;
    //用户ID
    private Long userId;
    //订单ID
    private Long orderId;
    //商品ID
    private Long goodsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}