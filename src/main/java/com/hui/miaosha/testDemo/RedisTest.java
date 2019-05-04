package com.hui.miaosha.testDemo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 14:46 2019\4\14 0014
 */
public class RedisTest {

    public static void main(String[] args){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxWaitMillis(3000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "47.94.213.240", 6379, 3000, "123456");
        Jedis jedis = jedisPool.getResource();
        String key1 = jedis.get("key1");
        System.out.println("key1 = " + key1);
        if (jedis != null){
            jedis.close();
        }

    }
}
