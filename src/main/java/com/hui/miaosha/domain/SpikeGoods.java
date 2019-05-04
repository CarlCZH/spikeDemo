package com.hui.miaosha.domain;

import java.util.Date;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 16:02 2019\4\15 0015
 */
public class SpikeGoods {

    private Long id;
    //商品id
    private Long goodsId;
    //秒杀价
    private Double spikePrice;
    //库存数量
    private Integer stockCount;
    //秒杀开始时间
    private Date startDate;
    //秒杀结束时间
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Double getSpikePrice() {
        return spikePrice;
    }

    public void setSpikePrice(Double spikePrice) {
        this.spikePrice = spikePrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
