package com.hui.miaosha.result;

/**
 * @Author: CarlChen
 * @Despriction: 返回结果封装类
 * @Date: Create in 22:06 2019\4\11 0011
 */
public class ResultMsg<T> {

    private int code;

    private String msg;

    private T data;

    /**
     * 成功时调用
     */
    public static <T> ResultMsg<T> SUCCESS(T data){
        return new ResultMsg<T>(data);
    }

    /**
     * 失败时调用
     */
    public static <T> ResultMsg<T> ERROR(CodeMsg codeMsg){
        return new ResultMsg<T>(codeMsg);
    }

    private ResultMsg(T data){
        this.code = 0;
        this.msg = "SUCCESS";
        this.data = data;
    }

    private ResultMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private ResultMsg(CodeMsg codeMsg){
        if (codeMsg != null){
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}
