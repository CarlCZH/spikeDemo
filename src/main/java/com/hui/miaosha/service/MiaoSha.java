package com.hui.miaosha.service;

import com.hui.miaosha.domain.OrderInfo;
import com.hui.miaosha.vo.SpikeGoodsVo;

/**
 * @Author: CarlChen
 * @Despriction: 秒杀的具体操作service
 * @Date: Create in 22:07 2019\4\21 0021
 */
public interface MiaoSha {

    /**
     * 进行秒杀操作,减少库存,增加订单信息与秒杀订单信息
     * @param userId
     * @param goodsInfo
     * @return
     */
    OrderInfo stockReduce(Long userId, SpikeGoodsVo goodsInfo);

}
