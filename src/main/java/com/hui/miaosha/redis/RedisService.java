package com.hui.miaosha.redis;

import com.alibaba.fastjson.JSON;
import com.hui.miaosha.redis.KeyPrefix.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: CarlChen
 * @Despriction: redis的操作类
 * @Date: Create in 12:45 2019\4\14 0014
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 获取单个数据对象
     * @param prefix 唯一的前缀
     * @param key 键
     * @param clazz 返回数据类型
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //拼接真正的唯一key
            String realKey = prefix.getPrefix() + "_" + key;
            String data = jedis.get(realKey);
            T realData = stringToBean(data, clazz);
            return realData;
        }finally {
            closeJedis(jedis);
        }
    }

    /**
     * 设置对象
     * @param prefix
     * @param key
     * @param value
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String dataStr = beanToString(value);
            if (dataStr == null || dataStr.length() <= 0){
                return false;
            }
            //拼接真正的唯一key
            String realKey = prefix.getPrefix() + "_" + key;
            //对是否存在过期时间进行判断
            if (prefix.expireSecond() > 0){
                jedis.setex(realKey, prefix.expireSecond(), dataStr);
            } else {
                jedis.set(realKey, dataStr);
            }
            return true;
        }finally {
            closeJedis(jedis);
        }
    }

    /**
     * 删除数据
     * @param prefix
     * @param key
     * @return
     */
    public Long del(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //拼接真正的唯一key
            String realKey = prefix.getPrefix() + "_" + key;
            return jedis.del(realKey);
        }finally {
            closeJedis(jedis);
        }
    }

    /**
     * 判断这个key是否存在
     */
    public boolean exists(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //拼接真正的唯一key
            String realKey = prefix.getPrefix() + "_" + key;
            return jedis.exists(realKey);
        }finally {
            closeJedis(jedis);
        }
    }

    /**
     * 自增加
     */
    public Long incr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //拼接真正的唯一key
            String realKey = prefix.getPrefix() + "_" + key;
            return jedis.incr(realKey);
        }finally {
            closeJedis(jedis);
        }
    }

    /**
     * 自减少
     */
    public Long decr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //拼接真正的唯一key
            String realKey = prefix.getPrefix() + "_" + key;
            return jedis.decr(realKey);
        }finally {
            closeJedis(jedis);
        }
    }

    /**
     * 将javaBean转化成json字符串
     * @param value
     */
    public static <T> String beanToString(T value) {
        if (value == null){
            return null;
        }
       return JSON.toJSONString(value);
    }

    /**
     * 根据数据类型对数据进行处理
     * @param data
     * @param clazz
     */
    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String data, Class<T> clazz) {
        if (data == null || data.length() <=0 || clazz == null){
            return null;
        }
        if (clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(data);
        } else if (clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(data);
        } else if (clazz == double.class || clazz == Double.class) {
            return (T) Double.valueOf(data);
        } else if (clazz == float.class || clazz == Float.class) {
            return (T) Float.valueOf(data);
        } else {
            return JSON.parseObject(data, clazz);
        }
    }

    private void closeJedis(Jedis jedis) {
        if (jedis != null){
            jedis.close();
        }

    }

}
