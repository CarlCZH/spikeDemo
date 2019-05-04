package com.hui.miaosha.result;

/**
 * @Author: CarlChen
 * @Despriction: 错误信息封装类
 * @Date: Create in 22:11 2019\4\11 0011
 */
public class CodeMsg {

    private int code;

    private String msg;

    //错误通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "SUCCESS");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常,请稍后再试!");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101,"参数校验异常：%s");
    //登录模块 5002XX
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");

    //商品模块 5003XX

    //订单模块 5004XX
    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单不存在");

    //秒杀模块 5005XX
    public static CodeMsg STOCK_OVER = new CodeMsg(500500, "库存不足!");
    public static CodeMsg REPEATE_SPIKE = new CodeMsg(500501, "您已秒杀过此商品,请勿重复下单!");
    public static CodeMsg ERROR_SPIKE = new CodeMsg(500502, "秒杀出错,请重新进行秒杀!");

    private CodeMsg(){}

    private CodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public CodeMsg fillArgs(Object... args){
       int code = this.code;
       String msg = String.format(this.msg, args);
       return new CodeMsg(code, msg);
    }


}
