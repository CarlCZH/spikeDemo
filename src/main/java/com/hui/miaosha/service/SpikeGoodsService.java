package com.hui.miaosha.service;

import com.hui.miaosha.vo.SpikeGoodsVo;

import java.util.List;

public interface SpikeGoodsService {

    /**
     * 查询所有秒杀商品信息
     * @return
     */
    List<SpikeGoodsVo> getSpikeGoodsInfo();

    /**
     * 根据商品编号查询秒杀商品信息
     * @param goodsId
     * @return
     */
    SpikeGoodsVo getInfoById(long goodsId);

    /**
     * 减少商品库存
     * @param goodsInfo
     * @return
     */
    Integer stockReduce(SpikeGoodsVo goodsInfo);
}
