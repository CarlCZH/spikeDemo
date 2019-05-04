package com.hui.miaosha.controller;

import com.hui.miaosha.domain.SpikeUser;
import com.hui.miaosha.domain.UserInfo;
import com.hui.miaosha.redis.KeyPrefix.SpikeUserPrefix;
import com.hui.miaosha.redis.RedisService;
import com.hui.miaosha.result.ResultMsg;
import com.hui.miaosha.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 21:51 2019\4\11 0011
 */
@Controller
@RequestMapping(value = "/sample")
public class SampleController {
    private static Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private UserService userService;

    @Autowired
    RedisService redisService;

    @GetMapping(value = "/hello")
    @ResponseBody
    public String sample(){
        return "Hello,this is first sample !";
    }

    //测试thymeleaf模板
    @GetMapping(value = "/thymeleaf")
    public String thymeleafSample(Model model){
        model.addAttribute("name","carlchen");
        return "hello";
    }


    @GetMapping(value = "/db/{id}")
    @ResponseBody
    public ResultMsg<UserInfo> getUserInfo(@PathVariable("id") int id){
        UserInfo userInfo = userService.getUserInfo(id);
        return ResultMsg.SUCCESS(userInfo);
    }

    @GetMapping(value = "/redis/set")
    @ResponseBody
    public ResultMsg<Boolean> testRedisSet(){
        SpikeUser spikeUser = new SpikeUser();
        spikeUser.setId(Long.valueOf("12345678999"));
        spikeUser.setNickName("张三");
        logger.info(SpikeUserPrefix.userPrefix.getPrefix()+ "_" + "test1");
        boolean test1 = redisService.set(SpikeUserPrefix.userPrefix, "test1", spikeUser);
        return ResultMsg.SUCCESS(true);
    }

    @GetMapping(value = "/redis/get")
    @ResponseBody
    public ResultMsg<SpikeUser> testRedisGet(){
        SpikeUser spikeUser = redisService.get(SpikeUserPrefix.userPrefix, "test1", SpikeUser.class);
        return ResultMsg.SUCCESS(spikeUser);
    }

}
