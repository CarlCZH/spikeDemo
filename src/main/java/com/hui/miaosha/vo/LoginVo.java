package com.hui.miaosha.vo;

import com.hui.miaosha.Validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author: CarlChen
 * @Despriction: 登录的接收参数类
 * @Date: Create in 18:02 2019\4\13 0013
 */
public class LoginVo {

    @NotNull
    @IsMobile
    private String moblie;

    @NotNull
    @Length(min = 5)
    private String password;

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
