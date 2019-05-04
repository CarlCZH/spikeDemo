package com.hui.miaosha.redis.KeyPrefix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 12:58 2019\4\14 0014
 */
public abstract class BasePrefix implements KeyPrefix {

    private static final Logger logger = LoggerFactory.getLogger(BasePrefix.class);

    private int expireSecond;

    private String strPrefix;

    public BasePrefix(String strPrefix) { //0代表永不过期
        this(0, strPrefix);
    }

    public BasePrefix(int expireSecond, String strPrefix) {
        this.expireSecond = expireSecond;
        this.strPrefix = strPrefix;
    }

    @Override
    public int expireSecond() {
        return expireSecond;
    }

    @Override
    public String getPrefix(){
        String className = this.getClass().getSimpleName();
        //根据类名称对prefix进行拼接，保证其唯一性
        String onlyPrefix = className + "_" + strPrefix;
        logger.info(className + "----------" + onlyPrefix);
        return onlyPrefix;
    }
}
