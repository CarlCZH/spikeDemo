package com.hui.miaosha.controller;

import com.hui.miaosha.domain.SpikeUser;
import com.hui.miaosha.redis.KeyPrefix.GoodsPrefix;
import com.hui.miaosha.redis.RedisService;
import com.hui.miaosha.result.CodeMsg;
import com.hui.miaosha.result.ResultMsg;
import com.hui.miaosha.service.SpikeGoodsService;
import com.hui.miaosha.service.SpikeUserService;
import com.hui.miaosha.vo.GoodsDetailVo;
import com.hui.miaosha.vo.SpikeGoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: CarlChen
 * @Despriction: 商品controller
 * @Date: Create in 15:17 2019\4\14 0014
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    SpikeUserService spikeUserService;

    @Autowired
    SpikeGoodsService spikeGoodsService;

    @Autowired
    RedisService redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

//    @GetMapping(value = "/to_list")
//    public String toGoodsList(Model model,
//                          @CookieValue(name = ContanstMsg.COOKIE_NAME_TOKEN, required = false) String cookieValue,
//                          @RequestParam(name = ContanstMsg.COOKIE_NAME_TOKEN, required = false) String requestValue,
//                          HttpServletResponse response){
//
//        if (StringUtils.isEmpty(cookieValue) && StringUtils.isEmpty(requestValue)){
//            return "login";
//        }
//        String tokenValue = StringUtils.isEmpty(cookieValue)?requestValue:cookieValue;
//        SpikeUser spikeUser = spikeUserService.getByToken(tokenValue, response);
//        model.addAttribute("spikeUser", spikeUser);
//        return "goods";
//    }

    /**
     * 页面级别缓存,使用的是thymeleaf模板 -- ThymeleafViewResolver
     * @param model
     * @param spikeUser
     * @return
     */
    @GetMapping(value = "/to_list")
    @ResponseBody
    public String toGoodsList(Model model, SpikeUser spikeUser, HttpServletRequest request, HttpServletResponse response) {
        //取redis中的缓存
        String htm = redisService.get(GoodsPrefix.goodsListPrefix, "", String.class);
        if (!StringUtils.isEmpty(htm)){
            return htm;
        }

        List<SpikeGoodsVo> goodsList = spikeGoodsService.getSpikeGoodsInfo();
        model.addAttribute("spikeUser", spikeUser);
        model.addAttribute("goodsList", goodsList);

        //如果不在缓存中,就进行手动的渲染
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        htm = thymeleafViewResolver.getTemplateEngine().process("goods_list", webContext);
        //判断渲染的模板是否为空，不为将其保存到redis中
        if (!StringUtils.isEmpty(htm)){
            redisService.set(GoodsPrefix.goodsListPrefix,"", htm);
        }
        return htm;
    }

    /**
     * 这是URL级别缓存 - 使用的是thymeleaf模板 -- ThymeleafViewResolver
     * @GetMapping(value = "/to_detail/{goodsId}", produce="text/html")
     * @ResponseBody
     * public String toGoodsDetail(Model model, SpikeUser spikeUser, @PathVariable("goodsId") long goodsId,
    HttpServletRequest request, HttpServletResponse response)
     */
    /**
     * 前后端分离
     * @param spikeUser
     * @param goodsId
     * @return
     */
    @GetMapping(value = "/detail/{goodsId}")
    //  @ResponseBody需要加上,不然thymeleaf会包解析找不到错误
    @ResponseBody
    public ResultMsg<GoodsDetailVo> toGoodsDetail(SpikeUser spikeUser, @PathVariable("goodsId") long goodsId) {

        SpikeGoodsVo spikeGoodsInfo = spikeGoodsService.getInfoById(goodsId);

        long startTime = spikeGoodsInfo.getStartDate().getTime();
        long endTime = spikeGoodsInfo.getEndDate().getTime();
        long nowTime = System.currentTimeMillis();

        //秒杀活动的状态
        int spikeStatus = 0;
        //秒杀活动的时间
        int spikeRemainSecond = 0;

        if (startTime > nowTime) { //秒杀活动没有开始
            spikeStatus = 0;
            spikeRemainSecond = (int) (startTime - nowTime) / 1000;
        } else if (endTime < nowTime) { //秒杀活动已经结束
            spikeStatus = 2;
            spikeRemainSecond = -1;
        } else { //秒杀活动正在进行
            spikeStatus = 1;
            spikeRemainSecond = 0;
        }
        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setSpikeStatus(spikeStatus);
        goodsDetailVo.setSpikeRemainSecond(spikeRemainSecond);
        goodsDetailVo.setSpikeGoodsVo(spikeGoodsInfo);
        goodsDetailVo.setSpikeUser(spikeUser);

        return ResultMsg.SUCCESS(goodsDetailVo);
    }

}