package com.hui.miaosha.controller;

import com.hui.miaosha.result.ResultMsg;
import com.hui.miaosha.service.SpikeUserService;
import com.hui.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: CarlChen
 * @Despriction: 登录类
 * @Date: Create in 17:44 2019\4\13 0013
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SpikeUserService spikeUserService;

    @GetMapping(value = "/")
    public String TologinPage(){
        return "login";
    }


    @PostMapping(value = "/in")
    @ResponseBody
    public ResultMsg<String> loginIn(HttpServletResponse response, @Valid LoginVo loginVo){
        //使用全局异常处理器handler对数据进行校验
        //判断密码是否正确或是否存在该用户
        spikeUserService.getUserById(response, loginVo);
        return ResultMsg.SUCCESS("登录成功");

    }
}
