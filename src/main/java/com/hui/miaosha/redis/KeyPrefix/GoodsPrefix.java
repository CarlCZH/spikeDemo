package com.hui.miaosha.redis.KeyPrefix;

/**
 * @Author: CarlChen
 * @Despriction: 商品详情页面的redis中的键
 * @Date: Create in 13:29 2019\5\3 0003
 */
public class GoodsPrefix extends BasePrefix {

    private static final int GOODS_LIST_EXPIRE_TIME = 60;

    private static final String STR_PREFIX = "GL";

    public GoodsPrefix(int expireSecond, String strPrefix) {
        super(expireSecond, strPrefix);
    }

    public static GoodsPrefix goodsListPrefix = new GoodsPrefix(GOODS_LIST_EXPIRE_TIME, STR_PREFIX);
}
