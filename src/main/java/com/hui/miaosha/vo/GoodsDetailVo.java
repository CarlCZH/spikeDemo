package com.hui.miaosha.vo;

import com.hui.miaosha.domain.SpikeUser;

/**
 * @Author: CarlChen
 * @Despriction: 商品详情页面静态化VO
 * @Date: Create in 15:01 2019\5\3 0003
 */
public class GoodsDetailVo {

    //秒杀活动的状态
    private int spikeStatus;
    //秒杀活动的时间
    private int spikeRemainSecond;
    //商品VO
    private SpikeGoodsVo spikeGoodsVo;
    private SpikeUser spikeUser;

    public int getSpikeStatus() {
        return spikeStatus;
    }

    public void setSpikeStatus(int spikeStatus) {
        this.spikeStatus = spikeStatus;
    }

    public int getSpikeRemainSecond() {
        return spikeRemainSecond;
    }

    public void setSpikeRemainSecond(int spikeRemainSecond) {
        this.spikeRemainSecond = spikeRemainSecond;
    }

    public SpikeGoodsVo getSpikeGoodsVo() {
        return spikeGoodsVo;
    }

    public void setSpikeGoodsVo(SpikeGoodsVo spikeGoodsVo) {
        this.spikeGoodsVo = spikeGoodsVo;
    }

    public SpikeUser getSpikeUser() {
        return spikeUser;
    }

    public void setSpikeUser(SpikeUser spikeUser) {
        this.spikeUser = spikeUser;
    }
}
