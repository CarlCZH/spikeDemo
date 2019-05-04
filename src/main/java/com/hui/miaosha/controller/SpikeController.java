package com.hui.miaosha.controller;

import com.hui.miaosha.domain.OrderInfo;
import com.hui.miaosha.domain.SpikeOrder;
import com.hui.miaosha.domain.SpikeUser;
import com.hui.miaosha.result.CodeMsg;
import com.hui.miaosha.result.ResultMsg;
import com.hui.miaosha.service.MiaoSha;
import com.hui.miaosha.service.SpikeGoodsService;
import com.hui.miaosha.service.SpikeOrderService;
import com.hui.miaosha.vo.OrderDetailVo;
import com.hui.miaosha.vo.SpikeGoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: CarlChen
 * @Despriction: 秒杀订单controller
 * @Date: Create in 20:37 2019\4\21 0021
 */
@Controller
@RequestMapping(value = "/spike")
public class SpikeController {

    private static Logger logger = LoggerFactory.getLogger(SpikeController.class);

    @Autowired
    SpikeOrderService spikeOrderService;

    @Autowired
    SpikeGoodsService spikeGoodsService;

    @Autowired
    MiaoSha miaoSha;

    @PostMapping(value = "/do_spike")
    @ResponseBody
    public ResultMsg<OrderInfo> doSpike(@RequestParam("goodsId") Long goodsId, SpikeUser spikeUser) {
        logger.info("goodsId:------" + goodsId);
        if (spikeUser == null){
            return ResultMsg.ERROR(CodeMsg.SESSION_ERROR);
        }

        //判断库存是否为零
        SpikeGoodsVo goodsInfo = spikeGoodsService.getInfoById(goodsId);
        Integer stockCount = goodsInfo.getStockCount();
        if (stockCount <= 0){
            return ResultMsg.ERROR(CodeMsg.STOCK_OVER);
        }

        //判断其是否重复下单
        SpikeOrder spikeOrderInfo = spikeOrderService.getOrderInfo(spikeUser.getId(), goodsId);
        if (spikeOrderInfo != null){
            return ResultMsg.ERROR(CodeMsg.REPEATE_SPIKE);
        }

        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoSha.stockReduce(spikeUser.getId(), goodsInfo);
        return ResultMsg.SUCCESS(orderInfo);
    }


}
