package com.hui.miaosha.config;

import com.hui.miaosha.contanst.ContanstMsg;
import com.hui.miaosha.domain.SpikeUser;
import com.hui.miaosha.service.SpikeUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: CarlChen
 * @Despriction: 实现HandlerMethodArgumentResolver类,对webMvc参数中的
 * SpikerUser类进行获取,与cookie操作
 * @Date: Create in 22:09 2019\4\14 0014
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    SpikeUserService spikeUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == SpikeUser.class;
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  @Nullable WebDataBinderFactory binderFactory) throws Exception {
        //通过webRequest获取request与response
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        //分别从request中根据token键值获取paramValue和cookieValue
        String paramValue = request.getParameter(ContanstMsg.COOKIE_NAME_TOKEN);
        String cookieValue = getCookieValue(request, ContanstMsg.COOKIE_NAME_TOKEN);
        if (StringUtils.isEmpty(paramValue) && StringUtils.isEmpty(cookieValue)){
            return null;
        }
        //优先获取cookieValue
        String tokenValue = StringUtils.isEmpty(cookieValue) ? paramValue : cookieValue;
        //根据token的值获取用户信息，并将值放入webMvc的Argument参数
        SpikeUser spikeUser = spikeUserService.getByToken(tokenValue, response);
        return spikeUser;
    }

    /**
     * 获取request中cookies的指定的cookieNameToken信息
     * @param request
     * @param cookieNameToken
     * @return
     */
    private String getCookieValue(HttpServletRequest request, String cookieNameToken) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(cookieNameToken)){
                return cookie.getValue();
            }
        }
        return null;

    }
}
