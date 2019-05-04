package com.hui.miaosha.redis.KeyPrefix;

/**
 * @Author: CarlChen
 * @Despriction: 秒杀用户唯一key
 * @Date: Create in 13:04 2019\4\14 0014
 */
public class SpikeUserPrefix extends BasePrefix {

    private static final int TOKE_EXPIRE_TIME = 3600 * 24 * 2;

    private static final String STR_PREFIX = "US";

    public SpikeUserPrefix(int expireSecond, String strPrefix) {
        super(expireSecond, strPrefix);
    }

    public static SpikeUserPrefix userPrefix = new SpikeUserPrefix(TOKE_EXPIRE_TIME, STR_PREFIX);

    public static SpikeUserPrefix commonUserPrefix = new SpikeUserPrefix(TOKE_EXPIRE_TIME, "CU");
}
