package com.hui.miaosha.domain;

import java.util.Date;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 15:54 2019\4\15 0015
 */
public class OrderInfo {

    private Long id;
    //用户ID
    private Long userId;
    //商品ID
    private Long goodsId;
    //收货地址ID
    private Long deliveryAddrId;
    //冗余过来的商品名称
    private String goodsName;
    //商品数量
    private Integer goodsCount;
    //商品单价
    private Double goodsPrice;
    //购买渠道 1-pc, 2-android, 3-ios
    private Integer orderChannel;
    //订单状态, 0-新建未支付，1-已支付，2-已发货， 3-已收货，4-已退款，5-已完成
    private Integer status;
    //订单的创建时间
    private Date createDate;
    //支付时间
    private Date payDate;

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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getDeliveryAddrId() {
        return deliveryAddrId;
    }

    public void setDeliveryAddrId(Long deliveryAddrId) {
        this.deliveryAddrId = deliveryAddrId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
