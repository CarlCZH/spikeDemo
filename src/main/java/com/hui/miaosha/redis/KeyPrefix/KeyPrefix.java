package com.hui.miaosha.redis.KeyPrefix;

public interface KeyPrefix {

    int expireSecond();

    String getPrefix();

}
