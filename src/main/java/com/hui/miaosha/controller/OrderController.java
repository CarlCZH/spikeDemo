package com.hui.miaosha.controller;

import com.hui.miaosha.domain.OrderInfo;
import com.hui.miaosha.domain.SpikeUser;
import com.hui.miaosha.redis.RedisService;
import com.hui.miaosha.result.CodeMsg;
import com.hui.miaosha.result.ResultMsg;
import com.hui.miaosha.service.OrderService;
import com.hui.miaosha.service.SpikeGoodsService;
import com.hui.miaosha.service.SpikeOrderService;
import com.hui.miaosha.vo.OrderDetailVo;
import com.hui.miaosha.vo.SpikeGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: CarlChen
 * @Despriction: 商品详情Controller
 * @Date: Create in 22:26 2019\5\3 0003
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    SpikeGoodsService spikeGoodsService;

    @Autowired
    RedisService redisService;

    @GetMapping(value = "/detail")
    @ResponseBody
    public ResultMsg<OrderDetailVo> getInfo(SpikeUser spikeUser, @RequestParam("orderId") Long orderId){
        if (spikeUser == null){
            return ResultMsg.ERROR(CodeMsg.SESSION_ERROR);
        }
        OrderInfo orderInfo = orderService.getOrderInfo(orderId);
        if (orderInfo == null){
            return ResultMsg.ERROR(CodeMsg.ORDER_NOT_EXIST);
        }
        SpikeGoodsVo goodsInfo = spikeGoodsService.getInfoById(orderInfo.getGoodsId());
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setOrderInfo(orderInfo);
        orderDetailVo.setGoodsInfo(goodsInfo);
        return ResultMsg.SUCCESS(orderDetailVo);
    }


}
