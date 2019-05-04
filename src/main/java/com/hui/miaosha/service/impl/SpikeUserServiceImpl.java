package com.hui.miaosha.service.impl;

import com.hui.miaosha.contanst.ContanstMsg;
import com.hui.miaosha.dao.SpikeUserDao;
import com.hui.miaosha.domain.SpikeUser;
import com.hui.miaosha.exception.GobalException;
import com.hui.miaosha.redis.KeyPrefix.SpikeUserPrefix;
import com.hui.miaosha.redis.RedisService;
import com.hui.miaosha.result.CodeMsg;
import com.hui.miaosha.service.SpikeUserService;
import com.hui.miaosha.utils.MD5Util;
import com.hui.miaosha.utils.UUIDUtil;
import com.hui.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: CarlChen
 * @Despriction:
 * @Date: Create in 18:33 2019\4\13 0013
 */
@Service
public class SpikeUserServiceImpl implements SpikeUserService {

    @Autowired
    SpikeUserDao spikeUserDao;

    @Autowired
    RedisService redisService;

    @Override
    public boolean getUserById(HttpServletResponse response, LoginVo loginVo) {
        //查询用户信息
        SpikeUser spikeUser = spikeUserDao.selectUserById(Long.valueOf(loginVo.getMoblie()));
        if (spikeUser == null){
            throw new GobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String passDB = spikeUser.getPassword();
        String salt = spikeUser.getSalt();

        //将从数据库中取出的salt和传入的密码进行MD5加密
        String dbPass = MD5Util.formPassToDBPass(loginVo.getPassword(), salt);
        if (!passDB.equals(dbPass)){
            throw new GobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成唯一session值，并将其保存到redis中作为分布式session
        String token = UUIDUtil.uuid();
        addCookie(token, response, spikeUser);
        return true;
    }

    /**
     * 并对cookie进行刷新,并返回用户信息
     * @param token
     * @param response
     * @return
     */
    @Override
    public SpikeUser getByToken(String token, HttpServletResponse response){
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        SpikeUser user = redisService.get(SpikeUserPrefix.userPrefix, token, SpikeUser.class);
        //延迟cookie的有效期
        if (user != null){
            addCookie(token, response, user);
        }
        return user;
    }

    /**
     * 将用户信息保存到redis和cookie中,将cookie放入response中
     * @param token
     * @param response
     * @param spikeUser
     */
    private void addCookie(String token, HttpServletResponse response, SpikeUser spikeUser) {
        //保存到Redis中
        redisService.set(SpikeUserPrefix.userPrefix, token, spikeUser);
        //保存cookie
        Cookie cookie = new Cookie(ContanstMsg.COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(SpikeUserPrefix.userPrefix.expireSecond());
        cookie.setPath("/");
        response.addCookie(cookie);
    }



}
