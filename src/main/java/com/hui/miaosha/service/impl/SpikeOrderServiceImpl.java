package com.hui.miaosha.service.impl;

import com.hui.miaosha.dao.SpikeOrderDao;
import com.hui.miaosha.domain.OrderInfo;
import com.hui.miaosha.domain.SpikeOrder;
import com.hui.miaosha.service.SpikeGoodsService;
import com.hui.miaosha.service.SpikeOrderService;
import com.hui.miaosha.vo.SpikeGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 20:53 2019\4\21 0021
 */
@Service
public class SpikeOrderServiceImpl implements SpikeOrderService {

    @Autowired
    SpikeOrderDao spikeOrderDao;

    @Override
    public SpikeOrder getOrderInfo(Long userId, Long goodsId) {
        return spikeOrderDao.selectOrderInfo(userId, goodsId);
    }

    @Override
    @Transactional
    public OrderInfo setOrderInfo(Long userId, SpikeGoodsVo goodsInfo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setGoodsId(goodsInfo.getId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsName(goodsInfo.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goodsInfo.getSpikePrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateDate(new Date());
        Long orderId = spikeOrderDao.insertOrderInfo(orderInfo);
        SpikeOrder spikeOrder = new SpikeOrder();
        spikeOrder.setUserId(userId);
        spikeOrder.setOrderId(orderId);
        spikeOrder.setGoodsId(goodsInfo.getId());
        spikeOrderDao.insertSpikerOrder(spikeOrder);
        return orderInfo;
    }

}
