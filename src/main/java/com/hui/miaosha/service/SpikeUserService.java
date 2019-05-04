package com.hui.miaosha.service;

import com.hui.miaosha.domain.SpikeUser;
import com.hui.miaosha.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 18:32 2019\4\13 0013
 */
public interface SpikeUserService {

    boolean getUserById(HttpServletResponse response, LoginVo loginVo);

    SpikeUser getByToken(String token, HttpServletResponse response);

}
