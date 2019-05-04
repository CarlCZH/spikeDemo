package com.hui.miaosha.vo;

import com.hui.miaosha.domain.Goods;

import java.util.Date;

/**
 * @Author: CarlChen
 * @Despriction: 秒杀商品的信息VO
 * @Date: Create in 17:18 2019\4\15 0015
 */
public class SpikeGoodsVo extends Goods{

    //秒杀价
    private Double spikePrice;
    //库存数量
    private Integer stockCount;
    //秒杀开始时间
    private Date startDate;
    //秒杀结束时间
    private Date endDate;

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
