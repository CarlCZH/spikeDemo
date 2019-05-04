package com.hui.miaosha.utils;

import java.util.UUID;

/**
 * @Author: CarlChen
 * @Despriction: UUID生成工具类
 * @Date: Create in 15:47 2019\4\14 0014
 */
public class UUIDUtil {

    public static String uuid(){
        String replace = UUID.randomUUID().toString().replace("-", "");
        return replace;
    }

}
