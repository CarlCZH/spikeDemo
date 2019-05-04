package com.hui.miaosha.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: CarlChen
 * @Despriction: MD5加密工具类
 * @Date: Create in 17:00 2019\4\13 0013
 */
public class MD5Util {

    private static final String salt = "1q2w3e4r";

    public static String md5(String sec){
        return DigestUtils.md5Hex(sec);
    }

    /**
     * 第一次MD5加密
     * @param inputPass
     * @return
     */
    public static String inputPassFormPass(String inputPass){
        String passSalt = "" + salt.charAt(0) + salt.charAt(1) + inputPass + salt.charAt(4) + salt.charAt(5);
        String md5Pass = MD5Util.md5(passSalt);
        return md5Pass;
    }

    /**
     * 第二次MD5加密，此密码存入数据库中
     * @param md5Pass
     * @return
     */
    public static String formPassToDBPass(String md5Pass, String salt){
        String passSalt = salt.charAt(0) + salt.charAt(1) + md5Pass + salt.charAt(4) + salt.charAt(5);
        String md5PassDB = MD5Util.md5(passSalt);
        return md5PassDB;
    }

    public static void main(String[] args){
        String inputPass = "123456";
        String md5Pass = inputPassFormPass(inputPass);
        System.out.println("md5Pass = " + md5Pass);
        String md5PassDB = formPassToDBPass("48e62bba87b717cfb3ca0af5eacbed91", "1q2w3e4r");
        System.out.println("md5PassDB = " + md5PassDB);

    }

}
